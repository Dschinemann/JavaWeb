package beans.sla;

import jakarta.inject.Named;

import java.io.Serializable;

@Named
public class RegraSLA implements Serializable {
    private  String cliente,responsavelPCM,
            regimeFiscal,tipoDeIntegracao, tipoDeMovimento,
            segmento,premissa, deadLineInBound, deadLineOutBound;
    private boolean ETIQUETAGEM;
    private int PRAZOEXTRA,MAXDESCDIA,PRAZOEXTRARECEBIMENTO, PRAZOREC,PRAZOEXP, MAXEXPEDICAO, PRAZOEXTRAEXPEDICAO;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getResponsavelPCM() {
        return responsavelPCM;
    }

    public void setResponsavelPCM(String responsavelPCM) {
        this.responsavelPCM = responsavelPCM;
    }

    public String getRegimeFiscal() {
        return regimeFiscal;
    }

    public void setRegimeFiscal(String regimeFiscal) {
        this.regimeFiscal = regimeFiscal;
    }

    public String getTipoDeIntegracao() {
        return tipoDeIntegracao;
    }

    public void setTipoDeIntegracao(String tipoDeIntegracao) {
        this.tipoDeIntegracao = tipoDeIntegracao;
    }

    public String getTipoDeMovimento() {
        return tipoDeMovimento;
    }

    public void setTipoDeMovimento(String tipoDeMovimento) {
        this.tipoDeMovimento = tipoDeMovimento;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getPremissa() {
        return premissa;
    }

    public void setPremissa(String premissa) {
        this.premissa = premissa;
    }

    public String getDeadLineInBound() {
        return deadLineInBound;
    }

    public void setDeadLineInBound(String deadLineInBound) {
        this.deadLineInBound = deadLineInBound;
    }

    public String getDeadLineOutBound() {
        return deadLineOutBound;
    }

    public void setDeadLineOutBound(String deadLineOutBound) {
        this.deadLineOutBound = deadLineOutBound;
    }

    public boolean isETIQUETAGEM() {
        return ETIQUETAGEM;
    }

    public void setETIQUETAGEM(boolean ETIQUETAGEM) {
        this.ETIQUETAGEM = ETIQUETAGEM;
    }

    public int getPRAZOEXTRA() {
        return PRAZOEXTRA;
    }

    public void setPRAZOEXTRA(int PRAZOEXTRA) {
        this.PRAZOEXTRA = PRAZOEXTRA;
    }

    public int getMAXDESCDIA() {
        return MAXDESCDIA;
    }

    public void setMAXDESCDIA(int MAXDESCDIA) {
        this.MAXDESCDIA = MAXDESCDIA;
    }

    public int getPRAZOEXTRARECEBIMENTO() {
        return PRAZOEXTRARECEBIMENTO;
    }

    public void setPRAZOEXTRARECEBIMENTO(int PRAZOEXTRARECEBIMENTO) {
        this.PRAZOEXTRARECEBIMENTO = PRAZOEXTRARECEBIMENTO;
    }

    public int getPRAZOREC() {
        return PRAZOREC;
    }

    public void setPRAZOREC(int PRAZOREC) {
        this.PRAZOREC = PRAZOREC;
    }

    public int getPRAZOEXP() {
        return PRAZOEXP;
    }

    public void setPRAZOEXP(int PRAZOEXP) {
        this.PRAZOEXP = PRAZOEXP;
    }

    public int getMAXEXPEDICAO() {
        return MAXEXPEDICAO;
    }

    public void setMAXEXPEDICAO(int MAXEXPEDICAO) {
        this.MAXEXPEDICAO = MAXEXPEDICAO;
    }

    public int getPRAZOEXTRAEXPEDICAO() {
        return PRAZOEXTRAEXPEDICAO;
    }

    public void setPRAZOEXTRAEXPEDICAO(int PRAZOEXTRAEXPEDICAO) {
        this.PRAZOEXTRAEXPEDICAO = PRAZOEXTRAEXPEDICAO;
    }

    public void salvarSLA(){

    }
}
