package org.almansa.app.domain;

public interface Entity<T>{
	T getId();
	void setId(T id);
}