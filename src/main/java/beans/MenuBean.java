package beans;

import database.SQL.MenuItens;
import jakarta.inject.Named;
import models.Menu.Menu;
import models.Menu.SubMenu;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

@Named

public class MenuBean implements Serializable {

    public static ArrayList<Menu> menuItem(int HANDLE) throws SQLException {
        return MenuItens.criarItensDoMenu(HANDLE);
    }
    public static ArrayList<SubMenu> subMenuItem(int HANDLE, int HANDLEUSUARIO) throws SQLException {
        return MenuItens.subMenu(HANDLE, HANDLEUSUARIO);
    }
    public String navegar(String comando){
        return "/view/restricted/"+comando;
    }

}
