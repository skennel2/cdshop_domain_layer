package org.almansa.app.domain.album;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class SongInAlbum {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    private Song song;

    @Column(name = "no", nullable = false)
    private int no;

    @Column(name = "is_single")
    private boolean isSingle;

    public SongInAlbum(Song song, int no, boolean isSingle) {
        super();
        this.song = song;
        this.no = no;
        this.isSingle = isSingle;
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
    
    /*
     * for jpa
     */
    protected SongInAlbum() {
        super();
    }
}
