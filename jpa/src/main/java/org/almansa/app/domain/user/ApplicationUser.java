package org.almansa.app.domain.user;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.almansa.app.converter.PasswordConverter;
import org.almansa.app.domain.NamedEntityBase;

@Entity
@Table(name = "APP_USER")
public class ApplicationUser extends NamedEntityBase {

    @Column(name = "login_id", nullable = false, length = 30, unique = true)
    private String loginId;

    @Column(name = "password", nullable = false, length = 30)
    @Convert(converter = PasswordConverter.class)
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PersonalInfomation personalInfomation;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "name_last_modified_date")
    private Date nameFieldLastModifiedDate;
    
    public ApplicationUser(String name, String loginId, String password) {
        super(name);
        this.loginId = loginId;
        this.password = password;
    }

    public ApplicationUser(String name, String loginId, String password, PersonalInfomation personalInfomation) {
        super(name);
        this.loginId = loginId;
        this.password = password;
        this.personalInfomation = personalInfomation;
    }

    protected ApplicationUser() {
    }
    
    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public PersonalInfomation getPersonalInfomation() {
        return personalInfomation;
    }

    public void setPersonalInfomation(PersonalInfomation personalInfomation) {
        this.personalInfomation = personalInfomation;
    }
    
    public Date getNameFieldlastModifiedDate() {
        return nameFieldLastModifiedDate;
    }
   
    public void changeName(String newName) {
        super.changeName(newName);
        this.nameFieldLastModifiedDate = new Date();
    }
    
    @PrePersist
    public void prePersist() {
        this.nameFieldLastModifiedDate = new Date();
    }
    
    @Override
    public String toString() {
        return "ApplicationUser [loginId=" + loginId + ", password=" + password + ", personalInfomation="
                + personalInfomation + "]";
    }

}
