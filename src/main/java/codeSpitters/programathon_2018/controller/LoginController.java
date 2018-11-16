/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeSpitters.programathon_2018.controller;


import codeSpitters.programathon_2018.model.Administrator;
import codeSpitters.programathon_2018.model.User;
import codeSpitters.programathon_2018.service.user.UserService;
import codeSpitters.programathon_2018.service.user.administrator.AdministratorService;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Login view controller
 *
 * @author JorgeRemon, Josué Cubero
 */
@Controller
@Scope("request")
public class LoginController implements Serializable {

    @Autowired
    UserService userService;

    @Autowired
    AdministratorService administratorService;

    private String userEmail;
    private String userPassword;


    public LoginController() {
    }

    public void authenticate() {

        String emailPattern = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher emailMatcher = pattern.matcher(userEmail);

        if (!emailMatcher.find()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El formato del correo no es válido.", ""));
            RequestContext.getCurrentInstance().update("frmLogin");
        } else {

            User user = userService.getUser(this.userEmail);

            if (user == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("No existe una cuenta asociada a ese correo"));
                return;
            }

            if (user.getPassword().equals(this.userPassword)) { // logs the user in and redirects to the medicalConsultation

                Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);

                Administrator administrator = this.administratorService.getAdministrator(user.getSsn());

                try {
                    if (administrator == null) {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/responsible/responsible-dashboard.html");
                    } else {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/administrator/admin-dashboard.html");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Credenciales inválidos."));
            }
        }
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}