package errors;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;


@ManagedBean
@RequestScoped
@Named
public class ErrorHandler {
    public String getStatusCode() {
        String val = String.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext().getRequestMap().get("jakarta.servlet.error.status_code"));
        return val;
    }

    public String getMessage() {
        String val = (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("jakarta.servlet.error.message");
        return val;
    }

    public String getExceptionType() {
        Object val = FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("jakarta.servlet.error.exception_type");

        if(val != null){
            return val.toString();
        }else{
            return null;
        }

    }
    public String getException() {
        Object val = FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("jakarta.servlet.error.exception");
        if(val != null){
            return val.toString();
        }else{
            return null;
        }
    }

    public String getRequestURI() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("jakarta.servlet.error.request_uri");
    }

    public String getServletName() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("jakarta.servlet.error.servlet_name");
    }

}
