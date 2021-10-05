package bases;

public class ConnectDB {
    //dados do banco
    private static  String NOME = "sa";
    private static  String PASSWORD = "#escalasoft123";
    private static  String DATABASE_URL =
            "jdbc:sqlserver://192.168.0.228:1433;"
                    + "DatabaseName=ESCALASOFT;"
                    + "user="+NOME+";"
                    + "password="+PASSWORD+";"
                    + "loginTimeout=30;";
    private static String URL = "http://192.168.0.228:42510/authorization";

    public static String getDatabaseUrl() {
        return DATABASE_URL;
    }

    public static String getURL() {
        return URL;
    }
}
