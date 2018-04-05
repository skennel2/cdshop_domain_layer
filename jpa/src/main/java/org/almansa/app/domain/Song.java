package org.almansa.app.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class Song extends EntityBase{

	@NonNull
	@Column(name="song_name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="owner_artist_id")
	private Artist ownerArtist;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable
	private List<Artist> artists; // many to many 의 관게는  @JoinTable을 이용해 별로의 테이블로 관리되게 설정한다.
	
	@ManyToOne
	@JoinColumn(name="producer_id")
	private Producer producer;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Artist getOwnerArtist() {
		return ownerArtist;
	}

	public void setOwnerArtist(Artist ownerArtist) {
		this.ownerArtist = ownerArtist;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	
	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	@Override
	public String toString() {
		return "Song [name=" + name + ", ownerArtist=" + ownerArtist + ", artists=" + artists + ", producer=" + producer
				+ "]";
	}
}