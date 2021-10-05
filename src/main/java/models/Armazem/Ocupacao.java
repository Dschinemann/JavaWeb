package models.Armazem;


import models.Cliente.Cliente;

public class Ocupacao extends Cliente {
    private int OCUPACAOPP;
    private String dataOcupacao;

    public Ocupacao(int ID, String nome, int OCUPACAOPP, String dataOcupacao) {
        super(ID, nome);
        this.OCUPACAOPP = OCUPACAOPP;
        this.dataOcupacao = dataOcupacao;
    }

    public int getOCUPACAOPP() {
        return OCUPACAOPP;
    }

    public void setOCUPACAOPP(int OCUPACAOPP) {
        this.OCUPACAOPP = OCUPACAOPP;
    }

    public String getDataOcupacao() {
        return dataOcupacao;
    }

    public void setDataOcupacao(String dataOcupacao) {
        this.dataOcupacao = dataOcupacao;
    }

    public Ocupacao(int ocupacao, String data){
        this.OCUPACAOPP = ocupacao;
        this.dataOcupacao = data;
    }

    public Ocupacao() {
        super();
    }
}
