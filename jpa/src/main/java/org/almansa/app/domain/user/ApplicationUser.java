package org.almansa.app.domain.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.almansa.app.domain.NamedEntityBase;

@Entity
@Table(name = "APP_USER")
public class ApplicationUser extends NamedEntityBase {

    @Column(name = "login_id", nullable = false, length = 30)
    private String loginId;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @OneToOne(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY) // value of mappedBy -> field
    private PersonalInfomation personalInfomation;
    
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
    
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public PersonalInfomation getPersonalInfomation() {
        return personalInfomation;
    }

    public void setPersonalInfomation(PersonalInfomation personalInfomation) {
        this.personalInfomation = personalInfomation;
    }

    /*
     * for jpa
     */
    protected ApplicationUser() {
        super(null);
    }

}
