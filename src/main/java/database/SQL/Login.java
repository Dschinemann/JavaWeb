package database.SQL;

import database.ConexaoBD;
import models.Usuario;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class Login {

    public static Usuario logar(String login, String senha) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(senha.getBytes("iso-8859-1"),0,senha.length());
        byte[] sha1hash = md.digest();
        String result = Base64.getEncoder().encodeToString(sha1hash);

        return usuarioDoBanco(
                "SELECT A.LOGIN,A.SENHA,A.PESSOA,A.HANDLE,A.PAPEL, B.NOME NOMEPAPEL, P.NOME"+
                " FROM MS_USUARIO A"+
                " INNER JOIN MS_PAPEL B ON B.HANDLE = A.PAPEL"+
                " INNER JOIN MS_PESSOA P ON P.HANDLE = A.PESSOA"+
                " WHERE A.LOGIN = "+"'"+login+"'"+
                " AND A.SENHA = "+"'"+result+"'"+
                " AND A.STATUS = 4"
        );
    }

    public static Usuario usuarioDoBanco (String sql) throws SQLException {
        Usuario usuario = null;
        ConexaoBD.criarConexaoComBD();
        ResultSet resultSet = ConexaoBD.consultarBancoDeDados(sql);
        while (resultSet.next()) {
             usuario = new Usuario(
                    resultSet.getString("NOME"),
                    resultSet.getInt("PAPEL"),
                    resultSet.getInt("PESSOA"),
                    resultSet.getInt("HANDLE")
            );
        }
        ConexaoBD.disconnect();
        return usuario;
    }
}
