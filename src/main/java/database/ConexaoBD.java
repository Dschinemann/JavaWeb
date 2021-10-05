package database;

import java.sql.*;

public class ConexaoBD {
    private static Connection conexao = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static final String NOME = "sa";
    private static final String PASSWORD = "#escalasoft123";
    private static final String DATABASE_URL =
            "jdbc:sqlserver://192.168.0.228:1433;"
                    + "DatabaseName=ESCALASOFT;"
                    + "user="+NOME+";"
                    + "password="+PASSWORD+";"
                    + "loginTimeout=30;";


    public static Connection criarConexaoComBD(){
        try  {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexao = DriverManager.getConnection(DATABASE_URL);
            statement = conexao.createStatement();
            System.out.print("Conectado com o banco");
            return conexao;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.print("Erro ao conectar com o banco");
            return null;
        }
    }

    public static void disconnect(){
        try{
            conexao.close();
            System.out.print("Desconectado com o banco");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ResultSet consultarBancoDeDados(String sql){
        try{
            statement = conexao.createStatement();
            resultSet = statement.executeQuery(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }
}
