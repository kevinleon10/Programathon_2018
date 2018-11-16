package codeSpitters.programathon_2018.model;
// Generated Sep 22, 2018 8:14:08 PM by Hibernate Tools 4.3.1


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="USER"
    ,catalog="CODE_SPITTERS"
    , uniqueConstraints = @UniqueConstraint(columnNames="EMAIL") 
)
public class User  implements java.io.Serializable, UserDetails{


     private String ssn;
     private String fullName;
     private String password;
     private int phoneNumber;
     private String email;
     private Administrator administrator;
     private Responsible responsible;

    public User() {
    }

	
    public User(String ssn, String fullName, String password, int phoneNumber, String email) {
        this.ssn = ssn;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public User(String ssn, String fullName, String password, int phoneNumber, String email, Administrator administrator, Responsible responsible) {
       this.ssn = ssn;
       this.fullName = fullName;
       this.password = password;
       this.phoneNumber = phoneNumber;
       this.email = email;
       this.administrator = administrator;
       this.responsible = responsible;
    }
   
     @Id 

    
    @Column(name="SSN", unique=true, nullable=false, length=35)
    public String getSsn() {
        return this.ssn;
    }
    
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    
    @Column(name="FULL_NAME", nullable=false, length=35)
    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    
    @Column(name="PASSWORD", nullable=false, length=250)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="PHONE_NUMBER", nullable=false)
    public int getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    @Column(name="EMAIL", unique=true, nullable=false, length=250)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

@OneToOne(fetch=FetchType.LAZY, mappedBy="user")
    public Administrator getAdministrator() {
        return this.administrator;
    }
    
    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

@OneToOne(fetch=FetchType.LAZY, mappedBy="user")
    public Responsible getResponsible() {
        return this.responsible;
    }
    
    public void setResponsible(Responsible responsible) {
        this.responsible = responsible;
    }

    public String getUsername() {
        return this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

