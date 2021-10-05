package models.Menu;

import java.io.Serializable;

public class Menu implements Serializable {
    private int HANDLE;
    private String descricao;


    public Menu(int HANDLE, String descricao) {
        this.HANDLE = HANDLE;
        this.descricao = descricao;
    }

    public int getHANDLE() {
        return HANDLE;
    }

    public void setHANDLE(int HANDLE) {
        this.HANDLE = HANDLE;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
