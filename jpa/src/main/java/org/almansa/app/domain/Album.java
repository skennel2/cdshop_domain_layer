package org.almansa.app.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.lang.NonNull;

/**
 * @author skenn
 *
 */
@Entity
public class Album{
	
	@javax.persistence.Id
	@GeneratedValue
	private Long Id;
	
	@NonNull
	@Column(name="artist_name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "album_artist_id")
	private Artist albumArtist;
	
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //FIXME 이경우는 곡에 대한 순서가 표현이 안된다. '앨범안의 곡'개념으로 다시 모델링한 클래스가 필요해보인다. 
	@JoinTable
	private List<Song> songs = new ArrayList<Song>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable
	private List<CategoryTag> tags = new ArrayList<CategoryTag>();
	
	@Enumerated(EnumType.STRING)
	private AlbumType albumType;
	
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

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
	public AlbumType getAlbumType() {
		return albumType;
	}

	public void setAlbumType(AlbumType albumType) {
		this.albumType = albumType;
	}
	
	public List<CategoryTag> getTags() {
		return tags;
	}

	public void setTags(List<CategoryTag> tags) {
		this.tags = tags;
	}
	
	public void addCategory(CategoryTag tag) {
		tags.add(tag);
	}

	@Override
	public String toString() {
		return "Album [Id=" + Id + ", name=" + name + ", albumArtist=" + albumArtist + ", releaseDate=" + releaseDate
				+ ", songs=" + songs + ", tags=" + tags + ", albumType=" + albumType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		Album other = (Album) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}
}