package org.almansa.app.test.mother;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.AlbumBuilder;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.domain.album.Song;

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
