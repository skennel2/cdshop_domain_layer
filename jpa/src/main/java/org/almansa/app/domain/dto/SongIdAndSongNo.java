package org.almansa.app.domain.dto;

import org.almansa.app.domain.Immutable;

public class SongIdAndSongNo implements Immutable{
    
    private int no;
    
    private Long songId;
        
    public SongIdAndSongNo(int no, Long songId) {
        super();
        this.no = no;
        this.songId = songId;
    }

    public int getNo() {
        return no;
    }

    public Long getSongId() {
        return songId;
    }
}