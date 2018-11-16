package codeSpitters.programathon_2018.controller;

import codeSpitters.programathon_2018.support.security.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Scope("request")
@ManagedBean
@RequestScoped
public class SessionController {

    private String userName;


    public SessionController() {
        userName = SecurityUtils.getLoggedInUser().getFullName();
    }

    public void logout(ActionEvent actionEvent) {
        SecurityContextHolder.clearContext();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
