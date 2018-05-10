package org.almansa.app.domain.value;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.almansa.app.domain.Immutable;

@Embeddable
public class EmailAddress implements Immutable {

    @Column(name = "email_address")
    private String emailAddress;

    protected EmailAddress() {
        super();
    }

    public EmailAddress(String emailAddress) {
        super();
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmailAddress other = (EmailAddress) obj;
        if (emailAddress == null) {
            if (other.emailAddress != null)
                return false;
        } else if (!emailAddress.equals(other.emailAddress))
            return false;
        return true;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public static boolean isFormatValid(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        return emailPattern.matcher(email).find();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        return result;
    }
}
