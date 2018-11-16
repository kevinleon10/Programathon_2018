/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeSpitters.programathon_2018.controller;

import codeSpitters.programathon_2018.model.User;
import codeSpitters.programathon_2018.service.user.UserService;
import codeSpitters.programathon_2018.service.user.responsible.ResponsibleService;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Sign Up view controller
 *
 * @author Renato Mainieri Sáenz
 */
@Component
@Scope("request")
@ManagedBean
@RequestScoped
public class SignUpController implements Serializable {

    private String userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userPassword;
    private String userConfirmPassword;

    @Autowired
    UserService userService;

    @Autowired
    ResponsibleService responsibleService;

    public SignUpController() { }

    public void createUser() {
        try {

            // Regex validations
            String idPattern = "^[0-9]{10}$";
            Pattern pattern = Pattern.compile(idPattern);
            Matcher idMatcher = pattern.matcher(userId);

            String emailPattern = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            pattern = Pattern.compile(emailPattern);
            Matcher emailMatcher = pattern.matcher(userEmail);

            String namePattern = "^[a-zA-Z ]{2,35}$";
            pattern = Pattern.compile(namePattern);
            Matcher nameMatcher = pattern.matcher(userName);

            String phonePattern = "^[0-9]{8}$";
            pattern = Pattern.compile(phonePattern);
            Matcher phoneMatcher = pattern.matcher(userPhone);

            String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@$%*()<>?:{}+\\-~]).{7,}$";
            pattern = Pattern.compile(passwordPattern);
            Matcher passwordMatcher = pattern.matcher(userPassword);

            String nonRepeatedFollowedCharacters = "^(?=.*$)((.)\\2?(?!\\2))+$";
            pattern = Pattern.compile(nonRepeatedFollowedCharacters);
            Matcher nonRepeatedFollowedCharactersMatcher = pattern.matcher(userPassword);

            // Check if the userEmail has the correct format

            if(!idMatcher.find()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cédula debe ser de 10 números", "" ));
                RequestContext.getCurrentInstance().update("frmLogin");
            } else if (!emailMatcher.find()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El formato del correo no es válido.", "" ));
                RequestContext.getCurrentInstance().update("frmLogin");
            } else if(!nameMatcher.find()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre debe contener mínimo 2 caracteres y máximo 35 caracteres. Ademas no puede contener números o caracteres especiales.", "" ));
                RequestContext.getCurrentInstance().update("frmLogin");
            } else if(!phoneMatcher.find()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El número de teléfono debe ser de 8 números.", "" ));
                RequestContext.getCurrentInstance().update("frmLogin");
            } else if(!passwordMatcher.find() || !nonRepeatedFollowedCharactersMatcher.find()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña debe tener mínimo 7 caracteres, una mayúscula, una mínuscula, un número, un caracter especial y no puede tener 2 caracteres iguales seguidos.", "" ));
                RequestContext.getCurrentInstance().update("frmLogin");
            }
            // Check if the email is already in use
            else if (userService.getUser(userEmail) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El correo ingresado ya está en uso, pruebe con otro", "" ));
                RequestContext.getCurrentInstance().update("frmLogin");
            }
            // Check if the id is already in use
            else if (userService.getUserBySsn(userId) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cédula ingresada ya está en uso", "" ));
                RequestContext.getCurrentInstance().update("frmLogin");
            }
            // Check if the password and the confirm password match
            else if (!Objects.equals(userPassword, userConfirmPassword)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas deben coincidir", "" ));
                RequestContext.getCurrentInstance().update("frmLogin");
            } else {
                userService.createUser(userId, userName, userEmail, userPassword, Integer.valueOf(userPhone));
                //SendMail sendMail = new SendMail();
                //sendMail.sendSignUpEmail(userEmail, userName);

                User user = userService.getUser(userEmail);
                responsibleService.createResponsible(user);
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/responsible/responsible-dashboard.html");
            }
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() { return userPhone; }

    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserConfirmPassword() {
        return userConfirmPassword;
    }

    public void setUserConfirmPassword(String confirmPassword) {
        this.userConfirmPassword = confirmPassword;
    }

}