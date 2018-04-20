package org.almansa.app.domain.user;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.almansa.app.domain.EntityBase;
import org.almansa.app.domain.value.EmailAddress;

@Entity
@Table(name = "APP_USER_PERSONAL_INFO")
public class PersonalInfomation extends EntityBase {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    private ApplicationUser user;

    @Embedded
    private EmailAddress email;

    @Temporal(TemporalType.DATE)
    private Date bornDate;

    @Lob
    @Column(name = "desc")
    private String description;

    public PersonalInfomation(ApplicationUser user, EmailAddress email, Date bornDate, String description) {
        super();
        this.user = user;
        this.email = email;
        this.bornDate = bornDate;
        this.description = description;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public String getDescription() {
        return description;
    }

    /*
     * for jpa
     */
    protected PersonalInfomation() {
        super();
    }

}
