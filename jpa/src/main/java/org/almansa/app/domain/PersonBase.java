package org.almansa.app.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class PersonBase extends EntityBase {
	@NonNull
	@Column(name = "artist_name")
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "born_date")
	private Date bornDate;

	public String getName() {
		return name;
	}

	public void changeName(String name) {
		this.name = name;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public void setBornDate(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month + 1, date);

		this.bornDate = cal.getTime();
	}

	@Override
	public String toString() {
		return "PersonBase [name=" + name + ", bornDate=" + bornDate + "]";
	}
}