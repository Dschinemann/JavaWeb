package models.Armazem;

public class QuantidadeDeOrdens {
    private String  mes;
    private int ANO, ENTRADAS, SAIDAS;

    public QuantidadeDeOrdens(String mes, int ANO, int ENTRADAS, int SAIDAS) {
        this.mes = mes;
        this.ANO = ANO;
        this.ENTRADAS = ENTRADAS;
        this.SAIDAS = SAIDAS;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getANO() {
        return ANO;
    }

    public void setANO(int ANO) {
        this.ANO = ANO;
    }

    public int getENTRADAS() {
        return ENTRADAS;
    }

    public void setENTRADAS(int ENTRADAS) {
        this.ENTRADAS = ENTRADAS;
    }

    public int getSAIDAS() {
        return SAIDAS;
    }

    public void setSAIDAS(int SAIDAS) {
        this.SAIDAS = SAIDAS;
    }
}
