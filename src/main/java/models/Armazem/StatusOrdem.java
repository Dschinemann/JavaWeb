package models.Armazem;

public class StatusOrdem {
    private int HANDLEORDEM, STATUSHANDLE, NUMEROORDEM;
    private String statusNome, cliente, pedido, notaFiscal,
            dataCadastro, inicioSeparacao, aguardandoCarregamento, carregado, dataHoraOrdemEncerrada;
    private String transportadora, clienteFinal, localEntrega, estado, municipio,dataCadastroFormat;

    public StatusOrdem(
            int HANDLEORDEM,
            int STATUSHANDLE,
            int NUMEROORDEM,
            String statusNome,
            String cliente,
            String pedido,
            String notaFiscal,
            String dataCadastro,
            String dataCadastroFormat,
            String inicioSeparacao,
            String aguardandoCarregamento,
            String carregado,
            String dataHoraOrdemEncerrada,
            String transportadora,
            String clienteFinal,
            String localEntrega,
            String estado,
            String municipio

    ) {
        this.HANDLEORDEM = HANDLEORDEM;
        this.STATUSHANDLE = STATUSHANDLE;
        this.NUMEROORDEM = NUMEROORDEM;
        this.statusNome = statusNome;
        this.cliente = cliente;
        this.pedido = pedido;
        this.notaFiscal = notaFiscal;
        this.dataCadastro = dataCadastro;
        this.dataCadastroFormat = dataCadastroFormat;
        this.inicioSeparacao = inicioSeparacao;
        this.aguardandoCarregamento = aguardandoCarregamento;
        this.carregado = carregado;
        this.transportadora = transportadora;
        this.clienteFinal = clienteFinal;
        this.localEntrega = localEntrega;
        this.estado = estado;
        this.municipio = municipio;
        this.dataHoraOrdemEncerrada = dataHoraOrdemEncerrada;

    }

    public int getHANDLEORDEM() {
        return HANDLEORDEM;
    }

    public void setHANDLEORDEM(int HANDLEORDEM) {
        this.HANDLEORDEM = HANDLEORDEM;
    }

    public int getSTATUSHANDLE() {
        return STATUSHANDLE;
    }

    public void setSTATUSHANDLE(int STATUSHANDLE) {
        this.STATUSHANDLE = STATUSHANDLE;
    }

    public int getNUMEROORDEM() {
        return NUMEROORDEM;
    }

    public void setNUMEROORDEM(int NUMEROORDEM) {
        this.NUMEROORDEM = NUMEROORDEM;
    }

    public String getStatusNome() {
        return statusNome;
    }

    public void setStatusNome(String statusNome) {
        this.statusNome = statusNome;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getInicioSeparacao() {
        return inicioSeparacao;
    }

    public void setInicioSeparacao(String inicioSeparacao) {
        this.inicioSeparacao = inicioSeparacao;
    }

    public String getAguardandoCarregamento() {
        return aguardandoCarregamento;
    }

    public void setAguardandoCarregamento(String aguardandoCarregamento) {
        this.aguardandoCarregamento = aguardandoCarregamento;
    }

    public String getCarregado() {
        return carregado;
    }

    public void setCarregado(String carregado) {
        this.carregado = carregado;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

    public String getClienteFinal() {
        return clienteFinal;
    }

    public void setClienteFinal(String clienteFinal) {
        this.clienteFinal = clienteFinal;
    }

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDataCadastroFormat() {
        return dataCadastroFormat;
    }

    public void setDataCadastroFormat(String dataCadastroFormat) {
        this.dataCadastroFormat = dataCadastroFormat;
    }

    public String getDataHoraOrdemEncerrada() {
        return dataHoraOrdemEncerrada;
    }

    public void setDataHoraOrdemEncerrada(String dataHoraOrdemEncerrada) {
        this.dataHoraOrdemEncerrada = dataHoraOrdemEncerrada;
    }
}
