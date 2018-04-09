package org.almansa.app.domain.user;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.almansa.app.domain.NamedEntityBase;

@Entity
@Table(name = "APP_USER")
public class ApplicationUser extends NamedEntityBase {

}
