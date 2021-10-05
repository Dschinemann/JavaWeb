package database.SQL;

import database.ConexaoBD;
import models.Menu.Menu;
import models.Menu.SubMenu;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuItens {
    public static ArrayList<Menu> criarItensDoMenu(int HANDLE) throws SQLException {

        ArrayList<Menu> menus = new ArrayList<>();
        String sql = "SELECT DISTINCT( B.NOME ), B.HANDLE" +
                " FROM MD_ICONE A" +
                " INNER JOIN MD_PAINELNAVEGACAO B ON B.HANDLE = A.PAINELNAVEGACAO" +
                " WHERE EXISTS (SELECT 1" +
                " FROM MS_USUARIO X" +
                " INNER JOIN MS_USUARIOPAPEL X1 ON X1.USUARIO = X.HANDLE" +
                " INNER JOIN MS_PAPELICONEPAINELNAVEGACAO X2 ON X2.PAPEL = X1.PAPEL" +
                " WHERE X2.DEFINICAOACESSO = 2" +
                " AND X.HANDLE = " + HANDLE +
                " AND X2.PAINELNAVEGACAO = A.HANDLE)" +
                " AND A.MODULO = 37";

        ConexaoBD.criarConexaoComBD();
        ResultSet resultSet = ConexaoBD.consultarBancoDeDados(sql);
        while (resultSet.next()) {
            Menu menu = new Menu(
                    resultSet.getInt("HANDLE"),
                    resultSet.getString("NOME")
            );
            menus.add(menu);
        }
        ConexaoBD.disconnect();
        return menus;
    }

    public static ArrayList<SubMenu> subMenu(int HANDLE, int HANDLEUSUARIO) throws SQLException {
        ArrayList<SubMenu> subMenus = new ArrayList<>();
        String sql = "SELECT A.TITULO, A.COMANDO\n" +
                "FROM MD_ICONE A\n" +
                "INNER JOIN MD_PAINELNAVEGACAO B ON B.HANDLE = A.PAINELNAVEGACAO\n" +
                "WHERE EXISTS (SELECT 1\n" +
                "FROM MS_USUARIO X\n" +
                "INNER JOIN MS_USUARIOPAPEL X1 ON X1.USUARIO = X.HANDLE\n" +
                "INNER JOIN MS_PAPELICONEPAINELNAVEGACAO X2 ON X2.PAPEL = X1.PAPEL\t\t\t\t \n" +
                "WHERE X2.DEFINICAOACESSO = 2\n" +
                "AND X.HANDLE = "+HANDLEUSUARIO+
                "AND X2.PAINELNAVEGACAO = A.HANDLE)\n" +
                "AND A.MODULO = 37\n" +
                "AND B.HANDLE ="+HANDLE;

        ConexaoBD.criarConexaoComBD();
        ResultSet resultSet = ConexaoBD.consultarBancoDeDados(sql);
        while (resultSet.next()) {

            String str = resultSet.getString("COMANDO");
            int indice = str.indexOf("view");
            String comando = str.substring(indice).replace(".php",".xhtml?faces-redirect=true");

            SubMenu subMenu = new SubMenu(
                    resultSet.getString("TITULO"),
                    comando
            );
            subMenus.add(subMenu);
        }
        ConexaoBD.disconnect();
        return subMenus;
    }
}
