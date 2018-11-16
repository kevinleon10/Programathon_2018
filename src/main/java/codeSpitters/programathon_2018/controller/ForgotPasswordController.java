/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeSpitters.programathon_2018.controller;

import codeSpitters.programathon_2018.service.mail.SendMail;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Forgot Password view controller
 *
 * @author Renato Mainieri Sáenz
 */
//@Component
//@Scope("request")
//@ManagedBean
//@RequestScoped
//public class ForgotPasswordController implements Serializable {
//
//    private String userEmail;
//    private String userName;
//    private String userCode;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private ActiveCodeService activeCodeService;
//
//    public ForgotPasswordController() {
//    }
//
//    /*
//    public void sendEmail() {
//        try {
//            User user = userService.getUser(userEmail);
//            if (user == null) {
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage("No existe una cuenta asociada a ese correo"));
//                return;
//            }
//            else {
//                userName = user.getFullName();
//                userCode = generateRandomCode();
//                ActivePasswordCodes passwordCodes = activeCodeService.getActiveCode(user);
//                if (passwordCodes == null) {
//                    activeCodeService.createActiveCode(user, userCode);
//                }
//                else {
//                    passwordCodes.setCode(userCode);
//                    activeCodeService.updateActiveCode(passwordCodes);
//                }
//                SendMail sendMail = new SendMail();
//                sendMail.sendRecoverPasswordEmail(userEmail, userName, userCode);
//                FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/forgot_password/forgot-password-confirmation.html");
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void redirectIndex(){
//        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/index.html");
//        } catch (IOException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private String generateRandomCode(){
//        String randomCode = RandomStringUtils.randomAlphabetic(3).toUpperCase() + "-" + RandomStringUtils.randomNumeric(3);
//        return randomCode;
//    }
//
//    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserCode() {
//        return userCode;
//    }
//
//    public void setUserCode(String userCode) {
//        this.userCode = userCode;
//    }
//    */
//}