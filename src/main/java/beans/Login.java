package beans;



import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import models.Usuario;
import servicos.WebService;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@Named
@SessionScoped
public class Login implements Serializable {
    private String nome;
    private String senha;
    private String token;
    private String mensagem = null;
    private boolean mostrar = false;
    private Usuario usuario;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public String getMenssagem() {
        return mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public String logarNoWebService() throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(!nome.isEmpty()){
            if(!senha.isEmpty()){
                String message = WebService.logar(nome, senha);
                int indice = message.indexOf("mensagem");
                if (indice > 0) {
                    this.mensagem = message.replace("\'"," ").trim();
                    this.mostrar = true;
                } else {
                    this.token = message;
                    usuario = database.SQL.Login.logar(nome,senha);
                    if(usuario == null){
                        this.mensagem = "Não foi possivel validar suas credenciais";
                        this.mostrar = true;
                    }else{
                        return  "/view/restricted/home.xhtml?faces-redirect=true";
                    }
                }
            }else{
                this.mensagem = "Campo senha não pode ser vazio";
                this.mostrar = true;
            }
        }else{
            this.mensagem = "Campo Usuário não pode ser vazio";
            this.mostrar = true;
        }
        return null;
    }

    public String logOut(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        usuario = null;
        return "/index.xhtml?faces-redirect=true";
    }
}
