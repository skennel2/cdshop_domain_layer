package org.almansa.app.domain.album;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
@Table(name = "CATEGORY_TAG")
public class CategoryTag {

    @Column(name = "category_name")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "CategoryTag [name=" + name + ", creationDate=" + creationDate + "]";
    }
}