package org.almansa.app.test.objectmother;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.AlbumBuilder;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.domain.album.Song;

/*
 * Object Mother
 * 테스트코드에서 오브젝트 생성코드를 제거한다.
 * 재사용을 통해 일관된 객체로 테스트할수 있게 해준다. 
 */
public final class AlbumObjectMother {

    public static Album createAlbum() {
        Lable illionaire = new Lable();
        illionaire.changeName("Illionaire");

        Artist theQ = new Artist();
        theQ.changeName("the quiett");
        theQ.changeLable(illionaire);
        theQ.setBornDate(1996, 1, 3);
        Song song1 = new Song("song1", theQ, null, "");
        Song song2 = new Song("song2", theQ, null, "");

        Album album = new AlbumBuilder().artist(theQ).thisIsLPType().name("Q Train").Build();
        album.addSong(song1, 1, false);
        album.addSong(song2, 2, false);
        return album;
    }
}
