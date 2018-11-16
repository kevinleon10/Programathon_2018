/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeSpitters.programathon_2018.controller;

import codeSpitters.programathon_2018.model.*;
import codeSpitters.programathon_2018.service.antecedent.AntecedentService;
import codeSpitters.programathon_2018.service.childrenService.ChildrenService;
import codeSpitters.programathon_2018.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Admin dahsboard controller
 *
 * @author JorgeRemon
 */
@Component
@Scope("request")
@ManagedBean
@RequestScoped
public class AdminChildrenController implements Serializable {


    @Autowired
    ChildrenService childrenService;

    @Autowired
    UserService userService;

    @Autowired
    AntecedentService antecedentService;

    private final HttpSession sesionHttp;

    private String childName;
    private String childSSN;
    private int childAge;
    private String childGender;
    private String childEthnicGroup;
    private List<Antecedent> antecedents;
    private List<Symptom> symptoms;
    private String responsibleSsn;
    private String responsibleName;
    private String responsibleMail;
    private int responsibleNumber;



    public AdminChildrenController() {
        sesionHttp = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

    }

    @PostConstruct
    public void init() {
        if (sesionHttp.getAttribute("CHILD_TRACKING_MOD_OJB") == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/admininstrator/children.html");
            } catch (IOException ex) {
                Logger.getLogger(AdminChildrenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Children children = (Children) sesionHttp.getAttribute("CHILD_TRACKING_MOD_OJB");
            User responsible = userService.getUserBySsn(children.getResponsible().getUserSsn());
            this.childName = children.getFullName();
            this.childAge = children.getAge();
            this.childGender = children.getGender();
            this.childSSN = children.getSsn();
            this.childEthnicGroup = children.getEthnicGroup();
            this.antecedents = antecedentService.getAntecedents(children.getSsn());
            this.responsibleSsn = responsible.getSsn();
            this.responsibleName = responsible.getFullName();
            this.responsibleMail = responsible.getEmail();
            this.responsibleNumber = responsible.getPhoneNumber();
        }
    }

    public void redirectChildrenList() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/administrator/children.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildSSN() {
        return childSSN;
    }

    public void setChildSSN(String childSSN) {
        this.childSSN = childSSN;
    }

    public int getChildAge() {
        return childAge;
    }

    public void setChildAge(int childAge) {
        this.childAge = childAge;
    }

    public String getChildGender() {
        return childGender;
    }

    public void setChildGender(String childGender) {
        this.childGender = childGender;
    }

    public String getChildEthnicGroup() {
        return childEthnicGroup;
    }

    public void setChildEthnicGroup(String childEthnicGroup) {
        this.childEthnicGroup = childEthnicGroup;
    }

    public List<Antecedent> getAntecedents() {
        return antecedentService.getAntecedents(this.childSSN);
    }

    public void setAntecedents(List<Antecedent> antecedents) {
        this.antecedents = antecedents;
    }


    public List<Symptom> getSymptoms() {
        return childrenService.getSymptoms((Children) sesionHttp.getAttribute("CHILD_TRACKING_MOD_OJB"));
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public String getResponsibleSsn() {
        return responsibleSsn;
    }

    public void setResponsibleSsn(String responsibleSsn) {
        this.responsibleSsn = responsibleSsn;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getResponsibleMail() {
        return responsibleMail;
    }

    public void setResponsibleMail(String responsibleMail) {
        this.responsibleMail = responsibleMail;
    }

    public int getResponsibleNumber() {
        return responsibleNumber;
    }

    public void setResponsibleNumber(int responsibleNumber) {
        this.responsibleNumber = responsibleNumber;
    }
}