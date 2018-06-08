package org.almansa.app.domain.dto;

import org.almansa.app.domain.Immutable;

public class SongIdAndSongNo implements Immutable {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + no;
		result = prime * result + ((songId == null) ? 0 : songId.hashCode());
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
		SongIdAndSongNo other = (SongIdAndSongNo) obj;
		if (no != other.no)
			return false;
		if (songId == null) {
			if (other.songId != null)
				return false;
		} else if (!songId.equals(other.songId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SongIdAndSongNo [no=" + no + ", songId=" + songId + "]";
	}
      
}