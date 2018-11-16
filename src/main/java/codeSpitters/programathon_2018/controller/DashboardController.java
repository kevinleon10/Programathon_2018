/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeSpitters.programathon_2018.controller;

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
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Login view controller
 *
 * @author JorgeRemon, Josué Cubero
 */
@Component
@Scope("request")
@ManagedBean
@RequestScoped
public class DashboardController implements Serializable {
//
//    @Autowired
//    PatientService patientService;
//
//    @Autowired
//    MedicalConsultationService medicalConsultationService;
//
//    private String userId;
//    private String userName;
//    private Date birthday;
//    private String phoneNumber;
//    private String bloodType;
//    private float height;
//
//    private float weight;
//
//    /*
//    public DashboardController() {
//        this.userId = SecurityUtils.getLoggedInUser().getIdentificator();
//        this.userName = SecurityUtils.getLoggedInUser().getFullName();
//    }
//
//    @PostConstruct
//    public void init() {
//        HttpServletRequest requestedURL = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//
//        if (requestedURL.getRequestURI().compareToIgnoreCase("/Programathon_2018/dashboard/dashboard.html") == 0) {
//            Patient loggedPatient = patientService.getPatient(userId);
//            if (loggedPatient.getBirth() != null) {
//                this.birthday = loggedPatient.getBirth();
//            }
//            if (loggedPatient.getBloodType() != null) {
//                this.bloodType = loggedPatient.getBloodType();
//            }
//            if (loggedPatient.getPhoneNumber() != null) {
//                this.phoneNumber = loggedPatient.getPhoneNumber();
//            }
//            this.height = loggedPatient.getHeight();
//            this.weight = loggedPatient.getWeight();
//        } else if (requestedURL.getRequestURI().compareToIgnoreCase("/Programathon_2018/administrator/admin-dashboard.html") == 0) {
//            //Admin variables.
//        }
//
//    }
//    */
//
//    /**
//     * Get a list of all the medical consultations on the system.
//     *
//     * @return a list of all the medical consultations on the system.
//     * @author Josué Cubero Sánchez.
//     */
////    public List<MedicalConsultation> getMedicalConsultations() {
////        return medicalConsultationService.getMedicalConsultations();
////    }
//
//    /**
//     * Get a list of all the medical consultations from the logged in user.
//     *
//     * @return a list of all the medical consultations from the logged in user.
//     * @author JorgeRemon.
//     */
////    public List<MedicalConsultation> getMedicalConsultationsByUser() {
////        return medicalConsultationService.getMedicalConsultationsByUser(this.userId);
////    }
////
////    public void birthChange(SelectEvent event) {
////        this.birthday = (Date) event.getObject();
////        Patient loggedPatient = patientService.getPatient(userId);
////        loggedPatient.setBirth(this.birthday);
////        patientService.updatePatient(loggedPatient);
////        FacesContext facesContext = FacesContext.getCurrentInstance();
////        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Feha de nacimeinto actualizada."));
////    }
////
////    public void phoneChange(ValueChangeEvent event) {
////        this.phoneNumber = (String) event.getNewValue();
////        Patient loggedPatient = patientService.getPatient(userId);
////        loggedPatient.setPhoneNumber(this.phoneNumber);
////        patientService.updatePatient(loggedPatient);
////        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Número telefónico actualizado."));
////        RequestContext.getCurrentInstance().update("frmSolicitudes");
////    }
////
////    public void bloodChange(ValueChangeEvent event) {
////
////        this.bloodType = (String) event.getNewValue();
////        Patient loggedPatient = patientService.getPatient(userId);
////        loggedPatient.setBloodType(this.bloodType);
////        patientService.updatePatient(loggedPatient);
////
////        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Tipo de sangre actualizado."));
////        RequestContext.getCurrentInstance().update("frmSolicitudes");
////    }
////
////    public void heightChange(ValueChangeEvent event) {
////        this.height = (float) event.getNewValue();
////        Patient loggedPatient = patientService.getPatient(userId);
////        loggedPatient.setHeight(this.height);
////        patientService.updatePatient(loggedPatient);
////
////        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Altura modificada."));
////        RequestContext.getCurrentInstance().update("frmSolicitudes");
////    }
////
////    public void weightChange(ValueChangeEvent event) {
////        this.weight = (float) event.getNewValue();
////        Patient loggedPatient = patientService.getPatient(userId);
////        loggedPatient.setWeight(this.weight);
////        patientService.updatePatient(loggedPatient);
////        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Peso modificado."));
////        RequestContext.getCurrentInstance().update("frmSolicitudes");
////    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
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
//    public Date getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(Date birthday) {
//        this.birthday = birthday;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getBloodType() {
//        return bloodType;
//    }
//
//    public void setBloodType(String bloodType) {
//        this.bloodType = bloodType;
//    }
//
//    public float getHeight() {
//        return height;
//    }
//
//    public void setHeight(float height) {
//        this.height = height;
//    }
//
//    public float getWeight() {
//        return weight;
//    }
//
//    public void setWeight(float weight) {
//        this.weight = weight;
//    }
}