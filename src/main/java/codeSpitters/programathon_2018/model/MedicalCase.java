package codeSpitters.programathon_2018.model;
// Generated Sep 22, 2018 8:14:08 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MedicalCase generated by hbm2java
 */
@Entity
@Table(name="MEDICAL_CASE"
    ,catalog="CODE_SPITTERS"
)
public class MedicalCase  implements java.io.Serializable {


     private Integer id;
     private Children children;
     private String name;
     private String description;
     private String summary;
     private String status;
     private boolean hasAppointment;
     private String prediagnostic;
     private Set symptoms = new HashSet(0);

    public MedicalCase() {
    }

	
    public MedicalCase(Children children, String name, String description, String summary, String status, boolean hasAppointment) {
        this.children = children;
        this.name = name;
        this.description = description;
        this.summary = summary;
        this.status = status;
        this.hasAppointment = hasAppointment;
    }
    public MedicalCase(Children children, String name, String description, String summary, String status, boolean hasAppointment, String prediagnostic, Set symptoms) {
       this.children = children;
       this.name = name;
       this.description = description;
       this.summary = summary;
       this.status = status;
       this.hasAppointment = hasAppointment;
       this.prediagnostic = prediagnostic;
       this.symptoms = symptoms;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CHILDREN_SSN", nullable=false)
    public Children getChildren() {
        return this.children;
    }
    
    public void setChildren(Children children) {
        this.children = children;
    }

    
    @Column(name="NAME", nullable=false)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="DESCRIPTION", nullable=false, length=500)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="SUMMARY", nullable=false, length=500)
    public String getSummary() {
        return this.summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }

    
    @Column(name="STATUS", nullable=false, length=15)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    
    @Column(name="HAS_APPOINTMENT", nullable=false)
    public boolean isHasAppointment() {
        return this.hasAppointment;
    }
    
    public void setHasAppointment(boolean hasAppointment) {
        this.hasAppointment = hasAppointment;
    }

    
    @Column(name="PREDIAGNOSTIC", length=65535)
    public String getPrediagnostic() {
        return this.prediagnostic;
    }
    
    public void setPrediagnostic(String prediagnostic) {
        this.prediagnostic = prediagnostic;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="medicalCase")
    public Set getSymptoms() {
        return this.symptoms;
    }
    
    public void setSymptoms(Set symptoms) {
        this.symptoms = symptoms;
    }




}


