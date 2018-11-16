/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeSpitters.programathon_2018.controller;

import codeSpitters.programathon_2018.model.Responsible;
import codeSpitters.programathon_2018.model.User;
import codeSpitters.programathon_2018.service.childrenService.ChildrenService;
import codeSpitters.programathon_2018.service.user.responsible.ResponsibleService;
import codeSpitters.programathon_2018.support.security.SecurityUtils;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Login view controller
 *
 * @author JorgeRemon, Josué Cubero
 */
@Component
@Scope("request")
@ManagedBean
@RequestScoped
public class AddChildController implements Serializable {

    @Autowired
    ChildrenService childrenService;

    @Autowired
    ResponsibleService responsibleService;

    private String childName;
    private String childSSN;
    private String childAge;
    private String childGender;
    private String childEthnicGroup;
    private String childRelation;
    ArrayList<String> ethnicGroups;
    ArrayList<String> genders;
    ArrayList<String> relations;

    public AddChildController() { }
    
    public void registerChild(){

        String namePattern = "^[a-zA-Z ]{2,35}$";
        Pattern pattern = Pattern.compile(namePattern);
        Matcher nameMatcher = pattern.matcher(childName);

        String idPattern = "^[0-9]{10}$";
        pattern = Pattern.compile(idPattern);
        Matcher idMatcher = pattern.matcher(childSSN);

        String agePattern = "^[0-9]{1,2}$";
        pattern = Pattern.compile(agePattern);
        Matcher ageMatcher = pattern.matcher(childAge);

        if(!nameMatcher.find()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre debe ser de al menos dos caracteres y de maximo 35 caracteres.", "" ));
            RequestContext.getCurrentInstance().update("frmAddChild");
        } else if(!idMatcher.find()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cédula debe ser de 10 números.", "" ));
            RequestContext.getCurrentInstance().update("frmAddChild");
        } else if(!ageMatcher.find()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La edad debe ser de uno o dos dígitos.", "" ));
            RequestContext.getCurrentInstance().update("frmAddChild");
        } else if(this.childEthnicGroup == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La etnia debe especificarse.", "" ));
            RequestContext.getCurrentInstance().update("frmAddChild");
        } else if(this.childRelation == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La relación debe especificarse.", "" ));
            RequestContext.getCurrentInstance().update("frmAddChild");
        } else if(this.childGender == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El género debe especificarse.", "" ));
            RequestContext.getCurrentInstance().update("frmAddChild");
        } else {
            User user = SecurityUtils.getLoggedInUser();
            assert user != null;
            Responsible responsible = this.responsibleService.getResponsible(user.getSsn());
            childrenService.createChild(responsible, childSSN, childName, Integer.valueOf(childAge), childRelation, childEthnicGroup, childGender);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/responsible/responsible-dashboard.html");
            } catch (IOException ex) {
                Logger.getLogger(AddChildController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cancelRegister(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/responsible/responsible-dashboard.html");
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

    public String getChildAge() {
        return childAge;
    }

    public void setChildAge(String childAge) {
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

    public String getChildRelation() { return childRelation; }

    public void setChildRelation(String childRelation) { this.childRelation = childRelation; }

    public List getGenders() {
        genders = new <String>ArrayList();
        genders.add(0, "Masculino");
        genders.add(1, "Femenino");
        return genders;
    }

    public List getRelations(){
        relations = new <String>ArrayList();
        relations.add(0, "Padre");
        relations.add(1, "Madre");
        relations.add(2, "Maestro");
        relations.add(3, "Encargado");
        relations.add(4, "Otro");
        return relations;
    }

    public void setGenders(ArrayList<String> genders) {
        this.genders = genders;
    }

    public List getEthnicGroups() {
        ethnicGroups = new <String>ArrayList();
        ethnicGroups.add(0, "Afro costarricense");
        ethnicGroups.add(1, "Asiático");
        ethnicGroups.add(2, "Blanco");
        ethnicGroups.add(3, "Mestizo");
        ethnicGroups.add(4, "Indígena");
        return ethnicGroups;
    }

    public void setEthnicGroups(ArrayList<String> genders) {
        this.genders = genders;
    }







}