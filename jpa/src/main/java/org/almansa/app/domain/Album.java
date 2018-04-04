package org.almansa.app.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.lang.NonNull;

//@Entity
public class Album{
	
	@javax.persistence.Id
	@GeneratedValue
	private Long Id;
	
	@NonNull
	@Column(name="artist_name")
	private String name;
	
	@OneToOne
	@JoinColumn(name = "album_artist_id")
	private Artist albumArtist;
	
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	
	@OneToMany(mappedBy="")
	private List<Song> numbers;
	
	private List<Genre> genres;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Artist getAlbumArtist() {
		return albumArtist;
	}

	public void setAlbumArtist(Artist albumArtist) {
		this.albumArtist = albumArtist;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<Song> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Song> numbers) {
		this.numbers = numbers;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
}