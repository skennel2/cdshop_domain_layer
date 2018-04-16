package org.almansa.app.domain.album;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.almansa.app.domain.NamedEntityBase;

@Entity
@Table(name = "IMAGE")
public class Image extends NamedEntityBase {

    @Column
    private String accessPath;

    @Column
    private String description;

    public Image(String name, String accessPath, String description) {
        super(name);
        this.accessPath = accessPath;
        this.description = description;
    }

    public String getAccessPath() {
        return accessPath;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * for jpa
     */
    protected Image() {
        super(null);
    }
}
