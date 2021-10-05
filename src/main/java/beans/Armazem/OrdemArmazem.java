package beans.Armazem;

import beans.Login;
import database.SQL.armazem.ConsultaStatusOrdem;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import models.Armazem.StatusOrdem;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class OrdemArmazem implements Serializable {
    private List<StatusOrdem> statusOrdems = new ArrayList<>();
    private List<StatusOrdem> statusOrdemsAbertas = new ArrayList<>();
    private int TOTALPEDIDOS,PSEPARACAO,PAGUARDANDOCARREGAMENTO,PFINALZADOS,PCANCELADOS;
    private final Login login;

    @Inject
    public OrdemArmazem(Login login) {
        this.login = login;
    }

    @PostConstruct
    public void init(){
        try{
            int HANDLEUSUARIOPESSOA = login.getUsuario().getEmpresa();
            listaDeOrdens(HANDLEUSUARIOPESSOA);
            listarOrdensAbertas();
            itensDosBoxes();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void listaDeOrdens(int HANDLEUSUARIOPESSOA) throws SQLException {
        this.statusOrdems = ConsultaStatusOrdem.consultarOrdens(HANDLEUSUARIOPESSOA);
    }


    public void listarOrdensAbertas(){
        this.statusOrdemsAbertas = statusOrdems.stream()
                .filter(o -> o.getSTATUSHANDLE() != 5 && o.getSTATUSHANDLE() != 6 )
                .collect(Collectors.toList());
    }

    public void itensDosBoxes(){
        for (StatusOrdem statusOrdem: statusOrdems){
            switch (statusOrdem.getSTATUSHANDLE()){
                case 33:
                    this.PSEPARACAO = PSEPARACAO + 1;
                    break;
                case 18:
                    this.PAGUARDANDOCARREGAMENTO = PAGUARDANDOCARREGAMENTO + 1;
                    break;
                case 5:
                    this.PFINALZADOS = PFINALZADOS + 1;
                    break;
                case 6:
                    this.PCANCELADOS = PCANCELADOS + 1;
                    break;
            }
        }
        this.TOTALPEDIDOS = statusOrdems.size();
    }

    public List<StatusOrdem> getStatusOrdems() {
        return statusOrdems;
    }

    public void setStatusOrdems(List<StatusOrdem> statusOrdems) {
        this.statusOrdems = statusOrdems;
    }

    public int getTOTALPEDIDOS() {
        return TOTALPEDIDOS;
    }

    public void setTOTALPEDIDOS(int TOTALPEDIDOS) {
        this.TOTALPEDIDOS = TOTALPEDIDOS;
    }

    public int getPSEPARACAO() {
        return PSEPARACAO;
    }

    public void setPSEPARACAO(int PSEPARACAO) {
        this.PSEPARACAO = PSEPARACAO;
    }

    public int getPAGUARDANDOCARREGAMENTO() {
        return PAGUARDANDOCARREGAMENTO;
    }

    public void setPAGUARDANDOCARREGAMENTO(int PAGUARDANDOCARREGAMENTO) {
        this.PAGUARDANDOCARREGAMENTO = PAGUARDANDOCARREGAMENTO;
    }

    public int getPFINALZADOS() {
        return PFINALZADOS;
    }

    public void setPFINALZADOS(int PFINALZADOS) {
        this.PFINALZADOS = PFINALZADOS;
    }

    public int getPCANCELADOS() {
        return PCANCELADOS;
    }

    public void setPCANCELADOS(int PCANCELADOS) {
        this.PCANCELADOS = PCANCELADOS;
    }

    public List<StatusOrdem> getStatusOrdemsAbertas() {
        return statusOrdemsAbertas;
    }

    public void setStatusOrdemsAbertas(List<StatusOrdem> statusOrdemsAbertas) {
        this.statusOrdemsAbertas = statusOrdemsAbertas;
    }

}
