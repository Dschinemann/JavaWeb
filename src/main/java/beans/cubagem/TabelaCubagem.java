package beans.cubagem;

import database.SQL.suporte.Cubagem;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import models.Ordem;

import java.io.Serializable;
import java.util.ArrayList;

@Named
@ViewScoped
public class TabelaCubagem implements Serializable {
    private ArrayList<Ordem> ordems = new ArrayList<>();
    private ArrayList<Ordem> ordemFiltrada = new ArrayList<>();
    private int cubagemDaOrdemFiltrada;
    private int ordemDoFiltro;
    private int PAGINA = 1;
    private boolean mostrarModal, mostrarTabela = false;
    private int quantRegistro;

    @PostConstruct
    private void init(){
        popularTabelaDeOrdens();
    }

    public int getQuantRegistro() {
        return quantRegistro;
    }

    public void setQuantRegistro(int quantRegistro) {
        this.quantRegistro = quantRegistro;
    }

    public boolean isMostrarTabela() {
        return mostrarTabela;
    }

    public void setMostrarTabela(boolean mostrarTabela) {
        this.mostrarTabela = mostrarTabela;
    }

    public ArrayList<Ordem> getOrdems() {
        return ordems;
    }

    public void setOrdems(ArrayList<Ordem> ordems) {
        this.ordems = ordems;
    }

    public ArrayList<Ordem> getOrdemFiltrada() {
        return ordemFiltrada;
    }

    public void setOrdemFiltrada(ArrayList<Ordem> ordemFiltrada) {
        this.ordemFiltrada = ordemFiltrada;
    }

    public int getCubagemDaOrdemFiltrada() {
        return cubagemDaOrdemFiltrada;
    }

    public void setCubagemDaOrdemFiltrada(int cubagemDaOrdemFiltrada) {
        this.cubagemDaOrdemFiltrada = cubagemDaOrdemFiltrada;
    }

    public int getOrdemDoFiltro() {
        return ordemDoFiltro;
    }

    public void setOrdemDoFiltro(int ordemDoFiltro) {
        this.ordemDoFiltro = ordemDoFiltro;
    }

    public int getPAGINA() {
        return PAGINA;
    }

    public void setPAGINA(int PAGINA) {
        this.PAGINA = PAGINA;
    }

    public boolean isMostrarModal() {
        return mostrarModal;
    }

    public void setMostrarModal(boolean mostrarModal) {
        this.mostrarModal = mostrarModal;
    }

    public void popularTabelaDeOrdens() {
        this.ordems = Cubagem.consultarCubagemDasOrdems(PAGINA);
        this.quantRegistro = ordems.size();
    }

    public void proximaPagina(){
        ArrayList<Ordem> novasOrdens = Cubagem.consultarCubagemDasOrdems(PAGINA);
        ordems.addAll(novasOrdens);
        this.PAGINA = PAGINA + 1;
        this.quantRegistro = ordems.size();
    }
    public void consultarOrdem(){
        ArrayList<Ordem> novasOrdens = Cubagem.consultarCubagemDaOrdem(getOrdemDoFiltro());
        for (Ordem o : novasOrdens){
            ordemFiltrada.add(o);
            this.cubagemDaOrdemFiltrada = cubagemDaOrdemFiltrada + o.getTOTAL_CUBICO();
        }
        this.mostrarModal = true;
    }
    public void fecharModal(){
        ordemFiltrada.clear();
        this.mostrarModal = false;
    }

}
