package beans.error;


import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

@ManagedBean
@SessionScoped
public class ErrorBean implements Serializable {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String navigate(){
        // Assume an exception has been thrown by some business logic
        System.out.println(10/0);
        return "anonymousView";
    }
}
