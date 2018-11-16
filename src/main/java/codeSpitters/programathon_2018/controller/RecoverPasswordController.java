/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeSpitters.programathon_2018.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Recover Password view controller
 *
 * @author Renato Mainieri Sáenz
 */
//@Component
//@Scope("request")
//@ManagedBean
//@RequestScoped
//public class RecoverPasswordController implements Serializable {
//
//    private String userName;
//    private String userCode;
//    private String userPassword;
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private ActiveCodeService activeCodeService;
//
//    public RecoverPasswordController() {
//    }
//
//    public void changePassword(){
//        User user = userService.getUser(userName);
//        if (user == null) {
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage("No existe una cuenta asociada a ese correo"));
//            return;
//        }
//        else if (!activeCodeService.getActiveCode(user).getCode().equals(userCode)) {
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage("El código de confirmación no es válido"));
//            return;
//        }
//        else {
//            user.setPassword(userPassword);
//            userService.updateUser(user);
//
//            ActivePasswordCodes passwordCodes = activeCodeService.getActiveCode(user);
//            activeCodeService.deleteActiveCode(passwordCodes);
//
//            Authentication authentication =
//                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            redirectDashboard();
//        }
//    }
//
//    public void redirectDashboard(){
//        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/dashboard/dashboard.html");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
//
//    public String getUserPassword() {
//        return userPassword;
//    }
//
//    public void setUserPassword(String userPassword) {
//        this.userPassword = userPassword;
//    }
//
//}