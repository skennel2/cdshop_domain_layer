package org.almansa.app.domain.value;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.almansa.app.domain.Immutable;

@Embeddable
public class Period implements Immutable {

    @Temporal(TemporalType.DATE)
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    private Date toDate;

    public Period(Date fromDate, Date toDate) throws NullPointerException, IllegalArgumentException {
        super();
        Objects.requireNonNull(fromDate, "fromDate can't be null");
        Objects.requireNonNull(toDate, "toDate can't be null");
        
        if (toDate.before(fromDate)) {
            throw new IllegalArgumentException("Error with dates");
        }
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    
    protected Period() {
    }    

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public boolean isIn(Date date) {
        if (date.after(fromDate) && date.before(toDate)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
        result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Period other = (Period) obj;
        if (fromDate == null) {
            if (other.fromDate != null)
                return false;
        } else if (!fromDate.equals(other.fromDate))
            return false;
        if (toDate == null) {
            if (other.toDate != null)
                return false;
        } else if (!toDate.equals(other.toDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Period [fromDate=" + fromDate + ", toDate=" + toDate + "]";
    }

}
