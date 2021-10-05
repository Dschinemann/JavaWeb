package models.Cliente;

public class Cliente {
    int ID;
    String nome;

    public Cliente(int ID, String nome) {
        this.ID = ID;
        this.nome = nome;
    }

    public Cliente() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
