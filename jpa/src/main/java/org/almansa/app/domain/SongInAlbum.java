package org.almansa.app.domain;

public class SongInAlbum {
	
	private Album album;
	
	private Song song;
	
	private int no;
	
	private boolean isSingle;
	
	public SongInAlbum() {
		super();
	}
	
	public SongInAlbum(Album album, Song song, int no, boolean isSingle) {
		super();
		this.album = album;
		this.song = song;
		this.no = no;
		this.isSingle = isSingle;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public boolean isSingle() {
		return isSingle;
	}

	public void setSingle(boolean isSingle) {
		this.isSingle = isSingle;
	}

	@Override
	public String toString() {
		return "SongInAlbum [album=" + album + ", song=" + song + ", no=" + no + ", isSingle=" + isSingle + "]";
	}
}
