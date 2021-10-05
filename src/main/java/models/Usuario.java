package models;

public class Usuario {
    private String nome;
    private int HANDLE,PAPELDOUSUARIO, USUARIOPESSOA;

    public Usuario(String nome, int PAPELDOUSUARIO, int USUARIOPESSOA, int HANDLE) {
        this.nome = nome;
        this.PAPELDOUSUARIO = PAPELDOUSUARIO;
        this.USUARIOPESSOA = USUARIOPESSOA;
        this.HANDLE = HANDLE;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPapelDoUsuario() {
        return PAPELDOUSUARIO;
    }

    public void setPapelDoUsuario(int papelDoUsuario) {
        this.PAPELDOUSUARIO = papelDoUsuario;
    }

    public int getEmpresa() {
        return USUARIOPESSOA;
    }

    public void setEmpresa(int PESSOA) {
        this.USUARIOPESSOA = PESSOA;
    }

    public int getHANDLE() {
        return HANDLE;
    }

    public void setHANDLE(int HANDLE) {
        this.HANDLE = HANDLE;
    }
}
