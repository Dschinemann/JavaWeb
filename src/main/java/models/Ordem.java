package models;

public class Ordem {
    private final int HANDLE;
    private final int ORDEM;
    private final int ICONE;
    private final String STATUS;
    private final String CLIENTE;
    private final String ITEM;
    private final int QUANTIDADE;
    private final int METRAGEMCUBICA;
    private final String DE;
    private final String PARA;

    public int getHANDLE() {
        return HANDLE;
    }

    public int getORDEM() {
        return ORDEM;
    }

    public int getICONE() {
        return ICONE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public String getCLIENTE() {
        return CLIENTE;
    }

    public String getITEM() {
        return ITEM;
    }

    public int getQUANTIDADE() {
        return QUANTIDADE;
    }

    public int getMETRAGEMCUBICA() {
        return METRAGEMCUBICA;
    }

    public String getDE() {
        return DE;
    }

    public String getPARA() {
        return PARA;
    }

    public String getUN_CONVERSAO() {
        return UN_CONVERSAO;
    }

    public String getNOME() {
        return NOME;
    }

    public String getDESCRICAO() {
        return DESCRICAO;
    }

    public int getTOTAL_CUBICO() {
        return TOTAL_CUBICO;
    }

    private final String UN_CONVERSAO;
    private final String NOME;
    private final String DESCRICAO;
    private final int TOTAL_CUBICO ;

    public Ordem(int handle, int ordem, int icone, String status, String cliente, String item, int quantidade, int metragemcubica, String de, String para, String un_conversao, String nome, String descricao, int total_cubico) {
        HANDLE = handle;
        ORDEM = ordem;
        ICONE = icone;
        STATUS = status;
        CLIENTE = cliente;
        ITEM = item;
        QUANTIDADE = quantidade;
        METRAGEMCUBICA = metragemcubica;
        DE = de;
        PARA = para;
        UN_CONVERSAO = un_conversao;
        NOME = nome;
        DESCRICAO = descricao;
        TOTAL_CUBICO = total_cubico;
    }
}
