package codeSpitters.programathon_2018.controller;

import codeSpitters.programathon_2018.model.*;
import codeSpitters.programathon_2018.service.childrenService.ChildrenService;
import codeSpitters.programathon_2018.service.mail.SendMail;
import codeSpitters.programathon_2018.service.symptom.SymptomService;
import codeSpitters.programathon_2018.service.user.UserService;
import codeSpitters.programathon_2018.service.user.responsible.ResponsibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
@Scope("request")
@ManagedBean
@RequestScoped
public class TimeEventController implements Serializable {

    private String symptoms;
    private Date date;
    private String Description;
    private String estado;
    private final HttpSession httpSession;

    @Autowired
    ChildrenService childrenService;

    @Autowired
    UserService userService;

    @Autowired
    SymptomService symptomService;

    @Autowired
    ResponsibleService responsibleService;

    public TimeEventController() {
        httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }


    public void doReturn() {
        try {
            httpSession.setAttribute("CHILD_TRACKING_MOD_ACT", "READ");
            httpSession.setAttribute("CHILD_TRACKING_MOD_OJB", httpSession.getAttribute("CHILD_TRACKING_MOD_OJB_CHILD"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/timeline/timeline.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startNotifications(){
        /*Children children = ((Children) httpSession.getAttribute("CHILD_TRACKING_MOD_OJB_CHILD"));
        User responsible = userService.getUserBySsn(children.getResponsible().getUserSsn());
        boolean hasAddedSymptoms = false;
        int i = 0;*/
        SendMail sendMail = new SendMail();
        /*while(!hasAddedSymptoms && i<15){
            try {*/
        try {
            sendMail.sendNotificationEmail("lskevin97@hotmail.com", "jeje", "jeje");
        } catch (IOException e) {
            e.printStackTrace();
        }
                /*Thread.sleep(150000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Date date = new Date();
            if(symptomService.hasAddedSymptoms(date)){
                hasAddedSymptoms = true;
            }
            ++i;*/
    }

    public String getDescription() {
        Date date = (Date) httpSession.getAttribute("CHILD_TRACKING_MOD_OJB");
        int mdId = ((Symptom) childrenService.getSymptom(date)).getMedicalCase().getId();
        this.Description = ((MedicalCase) childrenService.getMedicalCasesById(mdId)).getDescription();
        return ((MedicalCase) childrenService.getMedicalCasesById(mdId)).getDescription();
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEstado() {
        Date date = (Date) httpSession.getAttribute("CHILD_TRACKING_MOD_OJB");
        this.estado = ((Symptom) childrenService.getSymptom(date)).getPhysicalStatus();
        return ((Symptom) childrenService.getSymptom(date)).getPhysicalStatus();
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSymptoms() {
        Date date = (Date) httpSession.getAttribute("CHILD_TRACKING_MOD_OJB");
        this.symptoms = ((Symptom) childrenService.getSymptom(date)).getDescription();
        return ((Symptom) childrenService.getSymptom(date)).getDescription();
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public Date getDate() {
        return (Date) httpSession.getAttribute("CHILD_TRACKING_MOD_OJB");
    }

    public void setDate(Date date) {
        this.date = date;
    }
}