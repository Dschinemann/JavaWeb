package models.Menu;

public class SubMenu {
    private String titulo;
    private String comando;

    public SubMenu(String titulo, String comando) {
        this.titulo = titulo;
        this.comando = comando;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

}
