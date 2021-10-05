package beans.Armazem;

import beans.Login;
import com.google.gson.Gson;
import database.SQL.armazem.ArmazemEstoque;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import models.Armazem.Ocupacao;
import models.Armazem.QuantidadeDeOrdens;
import models.Armazem.SaldoDeEstoque;
import servicos.ClientesXML;


import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Named
@ViewScoped

public class EstoqueArmazem implements Serializable {

    private ArrayList<SaldoDeEstoque> saldoDeEstoques = new ArrayList<>();
    private ArrayList<QuantidadeDeOrdens> quantidadeDeOrdens = new ArrayList<>();
    private  ArrayList<Ocupacao> ocupacaoArrayList = new ArrayList<>();
    private  Map<String, Integer> map = new HashMap<>();
    private  ArrayList<Ocupacao> ocupacaoMes = new ArrayList<>();
    private int ocupacaoHoje;
    private boolean SHOWBUTTON = false;

    private int HANDLE;
    private int SALDOTOTAL, SALDODISPONIVEL, SALDOBLOQ, SALDOAVA;
    private final Login login;
    private String registrosJson;
    
    private ArrayList<SaldoDeEstoque> saldoCategoria = new ArrayList<>();
    private boolean renderTabelaModal = false;

    private boolean SHOWLOADING = true;

    @Inject
    public EstoqueArmazem(Login login) {
        this.login = login;
    }

    @PostConstruct
    public void init() {

        HANDLE = login.getUsuario().getEmpresa();
        try {
            estoqueMercadoria();
            quantOrdens();
            carregarDadosDaocupacao(HANDLE);
            carregarAtributosDosBoxes();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void carregarDadosDaocupacao(int handle) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy",new Locale("pt","BR"));
        DateTimeFormatter formato1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ocupacaoArrayList = ClientesXML.carregarDadosDoCliente(handle);

        try {
            int OCUPACAO = ocupacaoArrayList.get(0).getOCUPACAOPP();
            for (Ocupacao value : ocupacaoArrayList) {
                LocalDate mesAtual = LocalDate.parse(value.getDataOcupacao(), formato);
                if (mesAtual.isEqual(LocalDate.parse(LocalDate.now().toString(), formato1))) {
                    this.ocupacaoHoje = value.getOCUPACAOPP();
                }

                if (map.isEmpty()) {
                    map.put(mesAtual.getMonth()
                            .getDisplayName(TextStyle.FULL,new Locale("pt","BR")), OCUPACAO);
                    OCUPACAO = value.getOCUPACAOPP();
                } else {
                    if (map.containsKey(mesAtual.getMonth()
                            .getDisplayName(TextStyle.FULL,new Locale("pt","BR")))) {
                        if (map.get(mesAtual.getMonth()
                                .getDisplayName(TextStyle.FULL,new Locale("pt","BR"))) < value.getOCUPACAOPP()) {
                            map.replace(
                                    mesAtual.getMonth()
                                            .getDisplayName(TextStyle.FULL,new Locale("pt","BR")),
                                    map.get(mesAtual.getMonth().getDisplayName(TextStyle.FULL,new Locale("pt","BR"))),
                                    value.getOCUPACAOPP()
                            );
                        }
                    } else {
                        map.put(mesAtual.getMonth()
                                .getDisplayName(TextStyle.FULL,new Locale("pt","BR")), OCUPACAO);
                    }
                }
            }
            for (Map.Entry<String, Integer> entry: map.entrySet()){
                Ocupacao ocupacao = new Ocupacao(entry.getValue(), entry.getKey());
                ocupacaoMes.add(ocupacao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void estoqueMercadoria() throws SQLException {
        int PAGINA = 1;
        this.saldoDeEstoques = ArmazemEstoque.consultarEstoque(" ", PAGINA, HANDLE);
    }

    public void quantOrdens() throws SQLException {
        this.quantidadeDeOrdens = ArmazemEstoque.consultarQuantOrdens(HANDLE);
        this.registrosJson = new Gson().toJson(quantidadeDeOrdens);
    }

    private void carregarAtributosDosBoxes() {
        for (SaldoDeEstoque saldoDeEstoque : saldoDeEstoques) {
            this.SALDOTOTAL = SALDOTOTAL + saldoDeEstoque.getTOTAL();
            this.SALDODISPONIVEL = SALDODISPONIVEL + saldoDeEstoque.getDISPONIVEL();
            this.SALDOBLOQ = SALDOBLOQ + saldoDeEstoque.getBLOQUEADO();
            String estrutura = saldoDeEstoque.getEndereco().toLowerCase(Locale.ROOT);
            int ind = estrutura.indexOf("ava");

            if (ind >= 0) {
                this.SALDOAVA = SALDOAVA + saldoDeEstoque.getTOTAL();
            }
        }
    }

    public void estoqueCategoria(String tipo){

        if(tipo.equalsIgnoreCase("disponivel")){
            ArrayList<SaldoDeEstoque> saldoFiltrado = (ArrayList<SaldoDeEstoque>) saldoDeEstoques.stream()
                    .filter(s  -> s.getDISPONIVEL() != 0)
                    .collect(Collectors.toList());

            this.renderTabelaModal = true;
            this.saldoCategoria = saldoFiltrado;
            this.SHOWBUTTON = false;
            this.SHOWLOADING = false;
        }
        if(tipo.equalsIgnoreCase("bloqueado")){
            ArrayList<SaldoDeEstoque> saldoFiltrado = (ArrayList<SaldoDeEstoque>) saldoDeEstoques.stream()
                    .filter(s  -> s.getBLOQUEADO() != 0)
                    .collect(Collectors.toList());

            this.renderTabelaModal = true;
            this.saldoCategoria = saldoFiltrado;
            this.SHOWBUTTON = false;
            this.SHOWLOADING = false;
        }
        if(tipo.equalsIgnoreCase("ava")){
            for (SaldoDeEstoque saldoDeEstoque : saldoDeEstoques) {
                String estrutura = saldoDeEstoque.getEndereco().toLowerCase(Locale.ROOT);
                int ind = estrutura.indexOf("ava");
                if (ind >= 0) {
                    saldoCategoria.add(saldoDeEstoque);
                    this.renderTabelaModal = true;
                }
            }
            this.SHOWBUTTON = false;
            this.SHOWLOADING = false;
        }
        if(tipo.equalsIgnoreCase("total")){
            this.saldoCategoria.addAll(saldoDeEstoques);
            this.SHOWBUTTON = true;
            this.SHOWLOADING = false;
        }
        if(tipo.equalsIgnoreCase("limpar")){
            this.saldoCategoria.clear();
            this.SHOWLOADING = true;
        }
    }


    public boolean isSHOWBUTTON() {
        return SHOWBUTTON;
    }

    public void setSHOWBUTTON(boolean SHOWBUTTON) {
        this.SHOWBUTTON = SHOWBUTTON;
    }

    public ArrayList<QuantidadeDeOrdens> getQuantidadeDeOrdens() {
        return quantidadeDeOrdens;
    }

    public String getRegistrosJson() {
        return registrosJson;
    }

    public void setRegistrosJson(String registrosJson) {
        this.registrosJson = registrosJson;
    }

    public Login getLogin() {
        return login;
    }

    public int getSALDOTOTAL() {
        return SALDOTOTAL;
    }

    public void setSALDOTOTAL(int SALDOTOTAL) {
        this.SALDOTOTAL = SALDOTOTAL;
    }

    public int getSALDODISPONIVEL() {
        return SALDODISPONIVEL;
    }

    public void setSALDODISPONIVEL(int SALDODISPONIVEL) {
        this.SALDODISPONIVEL = SALDODISPONIVEL;
    }

    public int getSALDOBLOQ() {
        return SALDOBLOQ;
    }

    public void setSALDOBLOQ(int SALDOBLOQ) {
        this.SALDOBLOQ = SALDOBLOQ;
    }

    public int getSALDOAVA() {
        return SALDOAVA;
    }

    public void setSALDOAVA(int SALDOAVA) {
        this.SALDOAVA = SALDOAVA;
    }

    public ArrayList<Ocupacao> getOcupacaoArrayList() {
        return ocupacaoArrayList;
    }

    public void setOcupacaoArrayList(ArrayList<Ocupacao> ocupacaoArrayList) {
        this.ocupacaoArrayList = ocupacaoArrayList;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public ArrayList<Ocupacao> getOcupacaoMes() {
        return ocupacaoMes;
    }

    public void setOcupacaoMes(ArrayList<Ocupacao> ocupacaoMes) {
        this.ocupacaoMes = ocupacaoMes;
    }

    public int getOcupacaoHoje() {
        return ocupacaoHoje;
    }

    public void setOcupacaoHoje(int ocupacaoHoje) {
        this.ocupacaoHoje = ocupacaoHoje;
    }

    public ArrayList<SaldoDeEstoque> getSaldoDeEstoques() {
        return saldoDeEstoques;
    }

    public void setSaldoDeEstoques(ArrayList<SaldoDeEstoque> saldoDeEstoques) {
        this.saldoDeEstoques = saldoDeEstoques;
    }

    public List<SaldoDeEstoque> getSaldoCategoria() {
        return saldoCategoria;
    }

    public void setSaldoCategoria(ArrayList<SaldoDeEstoque> saldoCategoria) {
        this.saldoCategoria = saldoCategoria;
    }

    public boolean isRenderTabelaModal() {
        return renderTabelaModal;
    }

    public void setRenderTabelaModal(boolean renderTabelaModal) {
        this.renderTabelaModal = renderTabelaModal;
    }

    public boolean isSHOWLOADING() {
        return SHOWLOADING;
    }

    public void setSHOWLOADING(boolean SHOWLOADING) {
        this.SHOWLOADING = SHOWLOADING;
    }
}
