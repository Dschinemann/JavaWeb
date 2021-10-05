package database.SQL.armazem;

import database.ConexaoBD;
import models.Armazem.QuantidadeDeOrdens;
import models.Armazem.SaldoDeEstoque;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ArmazemEstoque {
    public static ArrayList<SaldoDeEstoque> consultarEstoque(String filtro, int PAGINA, int USUARIOPESSOA) throws SQLException {
        String sql = "SELECT A.HANDLE," +
                " B0.CODIGOREFERENCIA CODIGOREFERENCIA," +
                " B0.NOME PRODUTO," +
                " B1.APELIDO CLIENTE," +
                " B6.SIGLA UNIDADEMEDIDA," +
                " B8.NOME LOTE," +
                " B7.FABRICACAO FABRICACAO," +
                " B7.VALIDADE VALIDADE," +
                " B13.SIGLA TIPODOC," +
                " B12.NUMERO NUMERODOCUMENTO," +
                " B14.NUMEROPEDIDO NUMEROPEDIDO," +
                " B14.NUMERO ORDEM," +
                " B12.EMISSAO EMISSAODOCUMENTO," +
                " B15.NOME NATUREZAMERCADORIA," +
                " A.DISPONIVELQUANTIDADE DISPONIVELQUANTIDADE," +
                " A.BLOQUEADOQUANTIDADE BLOQUEADOQUANTIDADE, " +
                " A.RESERVADOQUANTIDADE RESERVADOQUANTIDADE," +
                " A.SALDOQUANTIDADE SALDOQUANTIDADE," +
                " A.SAIDAVIRTUALQUANTIDADE SAIDAVIRTUALQUANTIDADE," +
                " A.SALDOQUANTIDADEVOLUME SALDOQUANTIDADEVOLUME," +
                " A.SALDOPESOLIQUIDO SALDOPESOLIQUIDO," +
                " A.SALDOPESOBRUTO SALDOPESOBRUTO," +
                " A.SALDOQUANTIDADE SALDOQUANTIDADE," +
                " A.SALDOPESOLIQUIDO SALDOPESOLIQUIDO," +
                " A.SALDOPESOBRUTO SALDOPESOBRUTO," +
                " A.SALDOVALOR SALDOVALOR," +
                " B2.ESTRUTURA ENDEREÇO" +
                " FROM AM_SALDOESTOQUE A" +
                " LEFT JOIN MT_ITEM B0 ON A.ITEM = B0.HANDLE " +
                " LEFT JOIN MS_PESSOA B1 ON A.CLIENTE = B1.HANDLE " +
                " LEFT JOIN AM_DEPOSITOLOCALIZACAO B2 ON A.ENDERECO = B2.HANDLE " +
                " LEFT JOIN AM_DEPOSITO B3 ON B2.DEPOSITO = B3.HANDLE " +
                " LEFT JOIN AM_TIPOAREA B4 ON B2.TIPOAREA = B4.HANDLE " +
                " LEFT JOIN AM_UNITIZACAO B5 ON A.UNITIZACAO = B5.HANDLE " +
                " LEFT JOIN MT_UNIDADEMEDIDA B6 ON A.UNIDADEMEDIDA = B6.HANDLE " +
                " LEFT JOIN AM_ORDEMITEMLOTE B7 ON A.ITEMLOTE = B7.HANDLE " +
                " LEFT JOIN AM_LOTE B8 ON B7.LOTE = B8.HANDLE " +
                " LEFT JOIN AM_ORDEMCONTEINER B9 ON B7.ORDEMCONTEINER = B9.HANDLE " +
                " LEFT JOIN PA_CONTEINER B10 ON B9.CONTEINER = B10.HANDLE " +
                " LEFT JOIN AM_ORDEMITEM B11 ON B7.ORDEMITEM = B11.HANDLE " +
                " LEFT JOIN AM_ORDEMDOCUMENTO B12 ON B11.ORDEMDOCUMENTO = B12.HANDLE " +
                " LEFT JOIN GD_TIPOORIGINARIO B13 ON B12.TIPO = B13.HANDLE" +
                " LEFT JOIN AM_ORDEM B14 ON B7.ORDEM = B14.HANDLE " +
                " LEFT JOIN MT_NATUREZAMERCADORIA B15 ON B15.HANDLE = B0.NATUREZAMERCADORIA" +
                " WHERE A.SALDOQUANTIDADE > 0" +
                " AND A.CLIENTE = " + USUARIOPESSOA + " " +
                filtro +
                " ORDER BY CODIGOREFERENCIA ASC";
        return getSaldoDeEstoque(sql);
    }

    private static ArrayList<SaldoDeEstoque> getSaldoDeEstoque(String sql) throws SQLException {
        ArrayList<SaldoDeEstoque> saldoDeEstoques = new ArrayList<>();
        ConexaoBD.criarConexaoComBD();
        ResultSet resultSet = ConexaoBD.consultarBancoDeDados(sql);
        while (resultSet.next()) {

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime localDateTime = LocalDateTime.parse(resultSet.getString("EMISSAODOCUMENTO"),formato);
            DateTimeFormatter novoFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = localDateTime.format(novoFormato);

            SaldoDeEstoque saldoDeEstoque = new SaldoDeEstoque(
                    resultSet.getInt("HANDLE"),
                    resultSet.getString("CODIGOREFERENCIA"),
                    resultSet.getString("PRODUTO"),
                    resultSet.getString("NATUREZAMERCADORIA"),
                    resultSet.getString("CLIENTE"),
                    resultSet.getString("NUMERODOCUMENTO"),
                    dataFormatada,
                    resultSet.getString("NUMEROPEDIDO"),
                    resultSet.getString("LOTE"),
                    resultSet.getString("VALIDADE"),
                    resultSet.getString("UNIDADEMEDIDA"),
                    resultSet.getInt("DISPONIVELQUANTIDADE"),
                    resultSet.getInt("RESERVADOQUANTIDADE"),
                    resultSet.getInt("BLOQUEADOQUANTIDADE"),
                    resultSet.getInt("SALDOQUANTIDADE"),
                    resultSet.getInt("SALDOPESOBRUTO"),
                    resultSet.getFloat("SALDOVALOR"),
                    resultSet.getString("ENDEREÇO")
            );
            saldoDeEstoques.add(saldoDeEstoque);
        }
        ConexaoBD.disconnect();
        return saldoDeEstoques;
    }

    public static ArrayList<QuantidadeDeOrdens> consultarQuantOrdens(int USUARIOPESSOA) throws SQLException {
        String sql = "SELECT DISTINCT(MONTH(O.DATA)) MÊS, DATENAME(YEAR,O.DATA) ANO," +
                "(SELECT COUNT(O1.TIPO) FROM AM_ORDEM O1 " +
                " WHERE NOT O1.STATUS = 6 AND O1.CLIENTE = 10349 AND O1.TIPO =8 " +
                " AND DATENAME(MONTH,O1.DATA) = DATENAME(MONTH,O.DATA)" +
                " AND DATENAME(YEAR,O1.DATA) = DATENAME(YEAR,O.DATA)) ENTRADAS," +
                "(SELECT COUNT(O2.TIPO) FROM AM_ORDEM O2 " +
                " WHERE NOT O2.STATUS = 6 AND O2.CLIENTE = 10349 AND O2.TIPO =  11 " +
                " AND DATENAME(MONTH,O2.DATA) = DATENAME(MONTH,O.DATA) AND DATENAME(YEAR,O2.DATA) = DATENAME(YEAR,O.DATA)) SAIDAS" +
                " FROM AM_ORDEM O " +
                " WHERE CLIENTE = " + USUARIOPESSOA + " AND NOT STATUS = 6 AND O.TIPO IN(8,11)" +
                " AND DATENAME(YEAR,O.DATA) = DATENAME(YEAR,GETDATE())" +
                " ORDER BY MONTH(O.DATA)";
        return getQuantOrdens(sql);
    }

    private static ArrayList<QuantidadeDeOrdens> getQuantOrdens(String sql) throws SQLException {
        ArrayList<QuantidadeDeOrdens> quantidadeDeOrdens = new ArrayList<>();
        ConexaoBD.criarConexaoComBD();
        ResultSet resultSet = ConexaoBD.consultarBancoDeDados(sql);
        while (resultSet.next()) {
            String mes = new DateFormatSymbols().getMonths()[resultSet.getInt("MÊS") - 1];
            QuantidadeDeOrdens quantidadeDeOrdem = new QuantidadeDeOrdens(
                    mes,
                    resultSet.getInt("ANO"),
                    resultSet.getInt("ENTRADAS"),
                    resultSet.getInt("SAIDAS")

            );
            quantidadeDeOrdens.add(quantidadeDeOrdem);
        }
        ConexaoBD.disconnect();
        return quantidadeDeOrdens;
    }
}
