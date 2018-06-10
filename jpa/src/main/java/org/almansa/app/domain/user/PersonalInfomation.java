package org.almansa.app.domain.user;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.almansa.app.domain.EntityBase;
import org.almansa.app.domain.value.EmailAddress;

@Entity
@Table(name = "APP_USER_PERSONAL_INFO")
public class PersonalInfomation extends EntityBase {

    @Temporal(TemporalType.DATE)
    private Date bornDate;

    @Embedded
    private EmailAddress email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    private ApplicationUser user;

    public PersonalInfomation(ApplicationUser user, EmailAddress email, Date bornDate) {
        super();
        this.user = user;
        this.email = email;
        this.bornDate = bornDate;
    }
    
    protected PersonalInfomation() {
    }    

    public Date getBornDate() {
        return bornDate;
    }

    public EmailAddress getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "PersonalInfomation [bornDate=" + bornDate + ", email=" + email + ", user=" + user + "]";
    }
}
