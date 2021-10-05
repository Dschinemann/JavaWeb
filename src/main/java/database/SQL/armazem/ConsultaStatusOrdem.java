package database.SQL.armazem;

import database.ConexaoBD;
import models.Armazem.StatusOrdem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ConsultaStatusOrdem {
    /*public static void main(String[] args) {
        try{
            System.out.println(consultarOrdens(10349));
           // System.out.println(consultarOrdensAbertas(10349));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }*/

    public static List<StatusOrdem> consultarOrdensAbertas(int USUARIOPESSOA) throws SQLException {
        String sql =" SELECT ORD.HANDLE,ORD.STATUS STATUS, S.NOME NOME_STATUS, " +
                " ORD.CLIENTE, ORD.NUMERO ORDEM," +
                " ORD.NUMEROPEDIDO PEDIDO, ORD.NUMERODOCUMENTO NF," +
                " ORD.SOLICITACAO CADASTRADA," +
                " ( SELECT MAX(OE.TERMINO) FROM AM_ORDEMETAPA OE WHERE OE.ORDEM = ORD.HANDLE AND OE.ETAPAARMAZENAGEM = 9  ) 'FIM SEPARACAO'," +
                " ( SELECT MAX(OE.TERMINO) FROM AM_ORDEMETAPA OE WHERE OE.ORDEM = ORD.HANDLE AND OE.ETAPAARMAZENAGEM = 4  ) 'FIM DA CONFERENCIA'," +
                " ( SELECT MAX(OE.TERMINO) FROM AM_ORDEMETAPA OE WHERE OE.ORDEM = ORD.HANDLE AND OE.ETAPAARMAZENAGEM = 1  ) 'AGUARDANDO CARREGAMENTO'," +
                " ( SELECT MAX(P2.NOME) FROM MS_PESSOA P2 WHERE P2.HANDLE = ORD.LOCALENTREGA) 'LOCALENTREGA'," +
                " ( SELECT MAX(P.NOME) FROM MS_PESSOA P WHERE P.HANDLE = ORD.CLIENTEFINAL) 'CLIENTEFINAL'," +
                " ( SELECT MAX(M.NOME) FROM MS_MUNICIPIO M WHERE M.HANDLE = ORD.MUNICIPIODESTINO) 'CIDADE'," +
                " ( SELECT MAX(ET.NOME) FROM MS_ESTADO ET WHERE ET.HANDLE = ORD.UFDESTINO) 'ESTADO'," +
                " ( SELECT MAX(P1.APELIDO) FROM MS_PESSOA P1 WHERE P1.HANDLE = ORD.TRANSPORTADORA) 'TRANSPORTADORA'," +
                " ( IIF(ORD.STATUS = 6, null,IIF(ORD.STATUS = 5,"+
                "(SELECT MAX(CONVERT(VARCHAR,DATA,20)) FROM AM_ORDEMLOG WHERE ORDEM = ORD.HANDLE AND TIPOMENSAGEM = 345 ), null)))'ORDEM ENCERRADA'"+
                " FROM AM_ORDEM ORD" +
                " INNER JOIN AM_STATUSORDEM S ON S.HANDLE = ORD.STATUS " +
                " WHERE ORD.CLIENTE = "+USUARIOPESSOA+
                " AND ORD.TIPO NOT IN(20,5)" +
                " AND ORD.STATUS NOT IN(5,6)"+
                " ORDER BY ORD.LOGDATACADASTRO DESC";
        return getOrdens(sql);
    }


    public static List<StatusOrdem> consultarOrdens(int USUARIOPESSOA) throws SQLException {
        String sql =" SELECT ORD.HANDLE,ORD.STATUS STATUS, S.NOME NOME_STATUS, " +
                " ORD.CLIENTE, ORD.NUMERO ORDEM," +
                " ORD.NUMEROPEDIDO PEDIDO, ORD.NUMERODOCUMENTO NF," +
                " ORD.SOLICITACAO CADASTRADA," +
                " ( SELECT MAX(OE.TERMINO) FROM AM_ORDEMETAPA OE WHERE OE.ORDEM = ORD.HANDLE AND OE.ETAPAARMAZENAGEM = 9  ) 'FIM SEPARACAO'," +
                " ( SELECT MAX(OE.TERMINO) FROM AM_ORDEMETAPA OE WHERE OE.ORDEM = ORD.HANDLE AND OE.ETAPAARMAZENAGEM = 4  ) 'FIM DA CONFERENCIA'," +
                " ( SELECT MAX(OE.TERMINO) FROM AM_ORDEMETAPA OE WHERE OE.ORDEM = ORD.HANDLE AND OE.ETAPAARMAZENAGEM = 1  ) 'AGUARDANDO CARREGAMENTO'," +
                " ( SELECT MAX(P2.NOME) FROM MS_PESSOA P2 WHERE P2.HANDLE = ORD.LOCALENTREGA) 'LOCALENTREGA'," +
                " ( SELECT MAX(P.NOME) FROM MS_PESSOA P WHERE P.HANDLE = ORD.CLIENTEFINAL) 'CLIENTEFINAL'," +
                " ( SELECT MAX(M.NOME) FROM MS_MUNICIPIO M WHERE M.HANDLE = ORD.MUNICIPIODESTINO) 'CIDADE'," +
                " ( SELECT MAX(ET.NOME) FROM MS_ESTADO ET WHERE ET.HANDLE = ORD.UFDESTINO) 'ESTADO'," +
                " ( SELECT MAX(P1.APELIDO) FROM MS_PESSOA P1 WHERE P1.HANDLE = ORD.TRANSPORTADORA) 'TRANSPORTADORA'," +
                " ( IIF(ORD.STATUS = 6, null,IIF(ORD.STATUS = 5,"+
                       "(SELECT MAX(CONVERT(VARCHAR,DATA,20)) FROM AM_ORDEMLOG WHERE ORDEM = ORD.HANDLE AND TIPOMENSAGEM = 345 ), null)))'ORDEM ENCERRADA'"+
                " FROM AM_ORDEM ORD" +
                " INNER JOIN AM_STATUSORDEM S ON S.HANDLE = ORD.STATUS " +
                " WHERE ORD.CLIENTE = "+USUARIOPESSOA+
                " AND ORD.TIPO NOT IN(20,5)" +
                " ORDER BY ORD.LOGDATACADASTRO DESC";

        return getOrdens(sql);
    }

    private static List<StatusOrdem> getOrdens(String sql) throws SQLException {

        List<StatusOrdem> statusOrdems = new ArrayList<>();
        ConexaoBD.criarConexaoComBD();
        ResultSet resultSet = ConexaoBD.consultarBancoDeDados(sql);
        while (resultSet.next()) {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            DateTimeFormatter formatoOrdemEncerrada = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String dataCadastro = resultSet.getString("CADASTRADA");
            String dataHoraCadastro =  resultSet.getString("CADASTRADA");
            String dataHoraInicioSeparacaoFormat = resultSet.getString("FIM SEPARACAO");
            String dataHoraCarregamento = resultSet.getString("FIM DA CONFERENCIA");
            String dataHoraAguardandoCarregamento =  resultSet.getString("AGUARDANDO CARREGAMENTO");
            String dataHoraOrdemEncerrada = resultSet.getString("ORDEM ENCERRADA");


            if(dataHoraOrdemEncerrada != null){
                dataHoraOrdemEncerrada = LocalDateTime.parse(resultSet.getString("ORDEM ENCERRADA"),formatoOrdemEncerrada).format(formatoDataHora);
            }

            if(dataHoraInicioSeparacaoFormat != null ){
                dataHoraInicioSeparacaoFormat = LocalDateTime.parse(resultSet.getString("FIM SEPARACAO"),formato).format(formatoDataHora);
            }

            if(dataHoraCarregamento != null){
                dataHoraCarregamento = LocalDateTime.parse(resultSet.getString("FIM DA CONFERENCIA"),formato).format(formatoDataHora);
            }

            if(dataHoraAguardandoCarregamento != null){
                dataHoraAguardandoCarregamento = LocalDateTime.parse(resultSet.getString("AGUARDANDO CARREGAMENTO"),formato).format(formatoDataHora);
            }

            if(dataHoraCadastro != null){
                dataHoraCadastro = LocalDateTime.parse(resultSet.getString("CADASTRADA"),formato).format(formatoDataHora);
            }

            if(dataCadastro != null){
                dataCadastro = LocalDateTime.parse(resultSet.getString("CADASTRADA"),formato).format(formatoData);
            }

            StatusOrdem statusOrdem = new StatusOrdem(
                    resultSet.getInt("HANDLE"),
                    resultSet.getInt("STATUS"),
                    resultSet.getInt("ORDEM"),
                    resultSet.getString("NOME_STATUS"),
                    resultSet.getString("CLIENTE"),
                    resultSet.getString("PEDIDO"),
                    resultSet.getString("NF"),
                    dataHoraCadastro,
                    dataCadastro,
                    dataHoraInicioSeparacaoFormat,
                    dataHoraCarregamento,
                    dataHoraAguardandoCarregamento,
                    dataHoraOrdemEncerrada,
                    resultSet.getString("TRANSPORTADORA"),
                    resultSet.getString("CLIENTEFINAL"),
                    resultSet.getString("LOCALENTREGA"),
                    resultSet.getString("ESTADO"),
                    resultSet.getString("CIDADE")
            );
            statusOrdems.add(statusOrdem);
        }
        ConexaoBD.disconnect();
        return statusOrdems;
    }

}
