package org.almansa.app.domain.album;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.almansa.app.domain.IDescription;
import org.almansa.app.domain.NamedEntityBase;

@Entity
@Table(name = "IMAGE")
public class Image extends NamedEntityBase implements IDescription {

    @Column
    private String accessPath;

    @Column
    private String description;

    protected Image() {
        super(null);
    }

    public Image(String name, String accessPath, String description) {
        super(name);
        this.accessPath = accessPath;
        this.description = description;
    }

    @Override
    public void changeDescription(String description) {
        this.description = description;
    }

    public String getAccessPath() {
        return accessPath;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    
    
	@Override
	public String toString() {
		return "Image [accessPath=" + accessPath + ", description=" + description + "]";
	}
        
}
