package database.SQL.suporte;

import database.ConexaoBD;
import models.Ordem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cubagem {

    public static ArrayList<Ordem> consultarCubagemDaOrdem(int ORDEM){
        String sql = "SELECT OP.ORDEM HANDLE, O.NUMERO ORDEM,ST.HANDLE ICONE, ST.NOME STATUS, P.NOME CLIENTE,"+
                " C.CODIGO CODIGO, C.NOME ITEM, OP.QUANTIDADE, C.METRAGEMCUBICA,UN.SIGLA DE, UN2.SIGLA PARA,"+
                " I.QUANTIDADE 'UN_CONVERSÃO', CV.NOME, I.DESCRICAO,"+
                " OP.QUANTIDADE/NULLIF(I.QUANTIDADE,0) * C.METRAGEMCUBICA 'TOTAL_CUBICO'"+
                " FROM AM_ORDEMPROGRAMACAO OP"+
                " JOIN MT_ITEMFATORCONVERSAO I ON OP.ITEM = I.ITEM"+
                " JOIN MT_ITEM C ON OP.ITEM = C.HANDLE"+
                " JOIN AM_ORDEM O ON OP.ORDEM = O.HANDLE"+
                " JOIN MS_PESSOA P ON OP.CLIENTE = P.HANDLE"+
                " JOIN AM_STATUSORDEMPROGRAMACAO ST ON OP.STATUS = ST.HANDLE"+
                " JOIN MS_OPERACAOCONVERSAO CV ON I.OPERACAO = CV.HANDLE"+
                " JOIN MT_UNIDADEMEDIDA UN ON I.UNIDADEORIGEM = UN.HANDLE"+
                " JOIN MT_UNIDADEMEDIDA UN2 ON I.UNIDADEDESTINO = UN2.HANDLE"+
                " WHERE O.STATUS NOT IN(5,6) and UN2.SIGLA = 'CX'"+
                " AND O.NUMERO = "+ORDEM+
                " ORDER BY OP.ORDEM ASC";

        return getOrdems(sql);
    }

    private static ArrayList<Ordem> getOrdems(String sql) {
        ArrayList<Ordem> ordemProcurada = new ArrayList<>();

        try{
            ConexaoBD.criarConexaoComBD();
            ResultSet resultSet = ConexaoBD.consultarBancoDeDados(sql);
            while (resultSet.next()) {
                Ordem ordem = new Ordem(
                        resultSet.getInt("HANDLE"),
                        resultSet.getInt("ORDEM"),
                        resultSet.getInt("ICONE"),
                        resultSet.getString("STATUS"),
                        resultSet.getString("CLIENTE"),
                        resultSet.getString("ITEM"),
                        resultSet.getInt("QUANTIDADE"),
                        resultSet.getInt("METRAGEMCUBICA"),
                        resultSet.getString("DE"),
                        resultSet.getString("PARA"),
                        resultSet.getString("UN_CONVERSÃO"),
                        resultSet.getString("NOME"),
                        resultSet.getString("DESCRICAO"),
                        resultSet.getInt("TOTAL_CUBICO")
                );
                ordemProcurada.add(ordem);
            }
            ConexaoBD.disconnect();
            return ordemProcurada;
        }catch (SQLException e){
            e.printStackTrace();
            ConexaoBD.disconnect();
            return null;
        }
    }

    public static ArrayList<Ordem> consultarCubagemDasOrdems(int PAGINA){
        String sql = "SELECT OP.ORDEM HANDLE, O.NUMERO ORDEM,ST.HANDLE ICONE, ST.NOME STATUS, P.NOME CLIENTE,"+
                " C.CODIGO CODIGO, C.NOME ITEM, OP.QUANTIDADE, C.METRAGEMCUBICA,UN.SIGLA DE, UN2.SIGLA PARA,"+
                " I.QUANTIDADE 'UN_CONVERSÃO', CV.NOME, I.DESCRICAO, OP.QUANTIDADE/NULLIF(I.QUANTIDADE,0) * C.METRAGEMCUBICA 'TOTAL_CUBICO'"+
                " FROM AM_ORDEMPROGRAMACAO OP"+
                " JOIN MT_ITEMFATORCONVERSAO I ON OP.ITEM = I.ITEM"+
                " JOIN MT_ITEM C ON OP.ITEM = C.HANDLE"+
                " JOIN AM_ORDEM O ON OP.ORDEM = O.HANDLE"+
                " JOIN MS_PESSOA P ON OP.CLIENTE = P.HANDLE"+
                " JOIN AM_STATUSORDEMPROGRAMACAO ST ON OP.STATUS = ST.HANDLE"+
                " JOIN MS_OPERACAOCONVERSAO CV ON I.OPERACAO = CV.HANDLE"+
                " JOIN MT_UNIDADEMEDIDA UN ON I.UNIDADEORIGEM = UN.HANDLE"+
                " JOIN MT_UNIDADEMEDIDA UN2 ON I.UNIDADEDESTINO = UN2.HANDLE"+
                " WHERE O.STATUS NOT IN(5,6) and UN2.SIGLA = 'CX'"+
                " ORDER BY OP.ORDEM ASC"+
                " OFFSET("+PAGINA+"-1) * 15 ROWS"+
                " FETCH NEXT 15 ROWS ONLY;";

        return getOrdems(sql);
    }
}
