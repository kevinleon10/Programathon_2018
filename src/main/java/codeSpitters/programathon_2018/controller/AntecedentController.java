/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeSpitters.programathon_2018.controller;

import codeSpitters.programathon_2018.model.Antecedent;
import codeSpitters.programathon_2018.model.Children;
import codeSpitters.programathon_2018.service.antecedent.AntecedentService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Antecedent view controller
 *
 * @author Renato Mainieri
 */
@Component
@Scope("request")
@ManagedBean
@RequestScoped
public class AntecedentController implements Serializable {

    private Date antecedentDate;
    private String antecedentDisease;
    private String antecedentTreatment;
    private String antecedentDuration;
    private String antecedentMedicine;
    private Children children;

    private final HttpSession session;

    @Autowired
    AntecedentService antecedentService;

    public AntecedentController() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    @PostConstruct
    private void init(){
        children = (Children) session.getAttribute("CHILD_TRACKING_MOD_OJB");
        if (children == null) {
            redirectDashboard();
        }
    }

    public void submit() {
        int duration = Integer.parseInt(antecedentDuration);
        if (duration < 0 || duration > 365) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La duración debe ser de 0 a 365 días", ""));
            RequestContext.getCurrentInstance().update("frmSolicitudes");
        }
        else if (!isRecentDate()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha debe ser menor a seis meses atrás", ""));
            RequestContext.getCurrentInstance().update("frmSolicitudes");
        }
        else if (antecedentMedicine.length() < 2 || antecedentMedicine.length() > 30) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La medicina debe tener entre 1 y 30 caracteres", ""));
            RequestContext.getCurrentInstance().update("frmSolicitudes");
        }
        else if (antecedentTreatment.length() < 1 || antecedentTreatment.length() > 500) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tratamiento debe tener entre 1 y 500 caracteres", ""));
            RequestContext.getCurrentInstance().update("frmSolicitudes");
        } else if (antecedentDisease.length() < 1 || antecedentDisease.length() > 500) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La enfermedad debe tener entre 1 y 500 caracteres", ""));
            RequestContext.getCurrentInstance().update("frmSolicitudes");
        } else {
            Antecedent antecedent = new Antecedent(children, antecedentDisease, duration, antecedentTreatment, antecedentMedicine, antecedentDate, null);
            antecedentService.createAntecedent(antecedent);
            redirectDashboard();
        }
    }

    private boolean isRecentDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0,0 );
        calendar.add(Calendar.MONTH, -6);

        Date date = calendar.getTime();
        return (antecedentDate.after(date));
    }

    public void redirectDashboard() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/children/children.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Date getAntecedentDate() {
        return antecedentDate;
    }

    public void setAntecedentDate(Date antecedentDate) {
        this.antecedentDate = antecedentDate;
    }

    public String getAntecedentDisease() {
        return antecedentDisease;
    }

    public void setAntecedentDisease(String antecedentDisease) {
        this.antecedentDisease = antecedentDisease;
    }

    public String getAntecedentTreatment() {
        return antecedentTreatment;
    }

    public void setAntecedentTreatment(String antecedentTreatment) {
        this.antecedentTreatment = antecedentTreatment;
    }

    public String getAntecedentDuration() {
        return antecedentDuration;
    }

    public void setAntecedentDuration(String antecedentDuration) {
        this.antecedentDuration = antecedentDuration;
    }

    public String getAntecedentMedicine() {
        return antecedentMedicine;
    }

    public void setAntecedentMedicine(String antecedentMedicine) {
        this.antecedentMedicine = antecedentMedicine;
    }
}