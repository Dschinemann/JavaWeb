package models.Armazem;

public class SaldoDeEstoque {
    private int HANDLE;
    private String produto;
    private String descricaoProduto;
    private String naturezaMercadoria;
    private String cliente;
    private String NOTAFISCAL;
    private String dataEmissao;
    private String nrPedido;
    private String lote;
    private String validade;
    private String unidade;
    private String endereco;
    private int DISPONIVEL;
    private int RESERVADO;
    private int BLOQUEADO;
    private int TOTAL;
    private int PESOBRUTO;
    private float VALORDAMERCADORIA;

    public SaldoDeEstoque(int HANDLE,
                          String produto,
                          String descricaoProduto,
                          String naturezaMercadoria,
                          String cliente,
                          String NOTAFISCAL,
                          String dataEmissao,
                          String nrPedido,
                          String lote,
                          String validade,
                          String unidade,
                          int DISPONIVEL,
                          int RESERVADO,
                          int BLOQUEADO,
                          int TOTAL,
                          int PESOBRUTO,
                          float VALORDAMERCADORIA,
                          String endereco
    ) {
        this.HANDLE = HANDLE;
        this.produto = produto;
        this.descricaoProduto = descricaoProduto;
        this.naturezaMercadoria = naturezaMercadoria;
        this.cliente = cliente;
        this.NOTAFISCAL = NOTAFISCAL;
        this.dataEmissao = dataEmissao;
        this.nrPedido = nrPedido;
        this.lote = lote;
        this.validade = validade;
        this.unidade = unidade;
        this.DISPONIVEL = DISPONIVEL;
        this.RESERVADO = RESERVADO;
        this.BLOQUEADO = BLOQUEADO;
        this.TOTAL = TOTAL;
        this.PESOBRUTO = PESOBRUTO;
        this.VALORDAMERCADORIA = VALORDAMERCADORIA;
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getHANDLE() {
        return HANDLE;
    }

    public void setHANDLE(int HANDLE) {
        this.HANDLE = HANDLE;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String getNaturezaMercadoria() {
        return naturezaMercadoria;
    }

    public void setNaturezaMercadoria(String naturezaMercadoria) {
        this.naturezaMercadoria = naturezaMercadoria;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNOTAFISCAL() {
        return NOTAFISCAL;
    }

    public void setNOTAFISCAL(String NOTAFISCAL) {
        this.NOTAFISCAL = NOTAFISCAL;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getNrPedido() {
        return nrPedido;
    }

    public void setNrPedido(String nrPedido) {
        this.nrPedido = nrPedido;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getDISPONIVEL() {
        return DISPONIVEL;
    }

    public void setDISPONIVEL(int DISPONIVEL) {
        this.DISPONIVEL = DISPONIVEL;
    }

    public int getRESERVADO() {
        return RESERVADO;
    }

    public void setRESERVADO(int RESERVADO) {
        this.RESERVADO = RESERVADO;
    }

    public int getBLOQUEADO() {
        return BLOQUEADO;
    }

    public void setBLOQUEADO(int BLOQUEADO) {
        this.BLOQUEADO = BLOQUEADO;
    }

    public int getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(int TOTAL) {
        this.TOTAL = TOTAL;
    }

    public int getPESOBRUTO() {
        return PESOBRUTO;
    }

    public void setPESOBRUTO(int PESOBRUTO) {
        this.PESOBRUTO = PESOBRUTO;
    }

    public float getVALORDAMERCADORIA() {
        return VALORDAMERCADORIA;
    }

    public void setVALORDAMERCADORIA(float VALORDAMERCADORIA) {
        this.VALORDAMERCADORIA = VALORDAMERCADORIA;
    }

}
