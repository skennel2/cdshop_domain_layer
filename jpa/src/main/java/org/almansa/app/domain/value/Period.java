package org.almansa.app.domain.value;

import java.util.Date;

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

    public Period(Date fromDate, Date toDate) {
        super();

        if (toDate.before(this.fromDate)) {
            throw new IllegalArgumentException("toDate");
        }
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * for jpa
     */
    protected Period() {
        super();
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
}
