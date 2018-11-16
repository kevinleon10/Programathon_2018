/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeSpitters.programathon_2018.controller;

import codeSpitters.programathon_2018.model.Antecedent;
import codeSpitters.programathon_2018.model.Children;
import codeSpitters.programathon_2018.service.antecedent.AntecedentService;
import codeSpitters.programathon_2018.service.childrenService.ChildrenService;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Login view controller
 *
 * @author JorgeRemon, Josué Cubero
 */
@Component
@Scope("request")
@ManagedBean
@RequestScoped
public class ChildrenController implements Serializable {


    @Autowired
    ChildrenService childrenService;

    @Autowired
    AntecedentService antecedentService;

    private final HttpSession sesionHttp;

    private String childName;
    private String childSSN;
    private int childAge;
    private String childGender;
    private String childEthnicGroup;
    private List<Antecedent> antecedents;



    public ChildrenController() {
        sesionHttp = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

    }

    @PostConstruct
    public void init() {
        if (sesionHttp.getAttribute("CHILD_TRACKING_MOD_OJB") == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/children/children.html");
            } catch (IOException ex) {
                Logger.getLogger(ChildrenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Children children = (Children) sesionHttp.getAttribute("CHILD_TRACKING_MOD_OJB");
            this.childName = children.getFullName();
            this.childAge = children.getAge();
            this.childGender = children.getGender();
            this.childSSN = children.getSsn();
            this.childEthnicGroup = children.getEthnicGroup();
            this.antecedents = antecedentService.getAntecedents(children.getSsn());

        }
    }

    public void redirectChildrenList() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/children/children.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ageChange(ValueChangeEvent event) {
        this.childAge = (int) event.getNewValue();
        Children children = childrenService.getChild(this.childSSN);
        children.setAge(this.childAge);
        childrenService.update(children);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Edad modificada."));
        RequestContext.getCurrentInstance().update("frmSolicitudes");
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
}