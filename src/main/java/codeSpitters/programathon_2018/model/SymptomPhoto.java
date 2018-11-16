package codeSpitters.programathon_2018.model;
// Generated Sep 22, 2018 8:14:08 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SymptomPhoto generated by hbm2java
 */
@Entity
@Table(name="SYMPTOM_PHOTO"
    ,catalog="CODE_SPITTERS"
)
public class SymptomPhoto  implements java.io.Serializable {


     private Integer id;
     private Symptom symptom;
     private byte[] document;
     private String mimeType;
     private String description;
     private String name;
     private String source;

    public SymptomPhoto() {
    }

	
    public SymptomPhoto(Symptom symptom, byte[] document, String mimeType) {
        this.symptom = symptom;
        this.document = document;
        this.mimeType = mimeType;
    }
    public SymptomPhoto(Symptom symptom, byte[] document, String mimeType, String description, String name, String source) {
       this.symptom = symptom;
       this.document = document;
       this.mimeType = mimeType;
       this.description = description;
       this.name = name;
       this.source = source;
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
    @JoinColumn(name="SYMPTOM_ID", nullable=false)
    public Symptom getSymptom() {
        return this.symptom;
    }
    
    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    
    @Column(name="DOCUMENT", nullable=false)
    public byte[] getDocument() {
        return this.document;
    }
    
    public void setDocument(byte[] document) {
        this.document = document;
    }

    
    @Column(name="MIME_TYPE", nullable=false, length=50)
    public String getMimeType() {
        return this.mimeType;
    }
    
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    
    @Column(name="DESCRIPTION", length=65535)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="NAME", length=30)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="SOURCE", length=40)
    public String getSource() {
        return this.source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }




}

