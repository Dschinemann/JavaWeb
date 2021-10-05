package beans;


import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;


import java.io.Serializable;


@Named
@ViewScoped
public class Main implements Serializable {
    public static String irParaTabelaCubagem(){
        return "/view/cubagemdaordem.xhtml?faces-redirect=true";
    }
    public static String irParaRegrasSla(){
        return "/view/regrasla.xhtml?faces-redirect=true";
    }
}
