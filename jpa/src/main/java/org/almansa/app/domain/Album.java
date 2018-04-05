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
public class Album extends EntityBase{
	
	@NonNull
	@Column(name="artist_name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "album_artist_id")
	private Artist albumArtist;
	
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //FIXME �̰��� � ���� ������ ǥ���� �ȵȴ�. '�ٹ����� ��'�������� �ٽ� �𵨸��� Ŭ������ �ʿ��غ��δ�. 
	@JoinTable(name="ALBUM_SONGS")
	private List<Song> songs = new ArrayList<Song>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="ALBUM_CATEGORY_TAGS")
	private List<CategoryTag> tags = new ArrayList<CategoryTag>();
	
	@Enumerated(EnumType.STRING)
	private AlbumType albumType;

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
		return "Album [name=" + name + ", albumArtist=" + albumArtist + ", releaseDate=" + releaseDate + ", songs="
				+ songs + ", tags=" + tags + ", albumType=" + albumType + "]";
	}	
}