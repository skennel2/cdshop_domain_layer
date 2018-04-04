package org.almansa.app.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class Song{
	@Id
	@GeneratedValue
	private Long id;
	
	@NonNull
	@Column(name="song_name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="owner_artist_id")
	private Artist ownerArtist;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable
	private List<Artist> artists; // many to many 의 관계는 @JoinTable로 따로 테이블을 만들어서 관리하도록 설정하면 좋다.
	
	@ManyToOne
	@JoinColumn(name="producer_id")
	private Producer producer;

	public Song(Long id, String name, Artist ownerArtist, Producer producer) {
		super();
		this.id = id;
		this.name = name;
		this.ownerArtist = ownerArtist;
		this.producer = producer;
	}

	public Song() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		return "Song [id=" + id + ", name=" + name + ", ownerArtist=" + ownerArtist + ", producer=" + producer + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Song other = (Song) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	} 
}