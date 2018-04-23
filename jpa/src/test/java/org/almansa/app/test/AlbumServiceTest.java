package org.almansa.app.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.almansa.app.AppConfig;
import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.AlbumBuilder;
import org.almansa.app.domain.album.AlbumType;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.Genre;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.domain.album.ProducerRole;
import org.almansa.app.domain.album.Song;
import org.almansa.app.domain.dto.AlbumSimpleViewModel;
import org.almansa.app.domain.dto.SongIdAndSongNo;
import org.almansa.app.service.AlbumAssembler;
import org.almansa.app.service.AlbumService;
import org.almansa.app.service.dto.AddAlbumRequest;
import org.almansa.app.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
public class AlbumServiceTest {

    @Autowired
    private AlbumAssembler albumAssembler;

    @Autowired
    private AlbumService albumService;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void addAlbumTest() {
        // assign
        AddAlbumRequest albumAddPamareters = new AddAlbumRequest();
        albumAddPamareters.setAlbumName("NEW AGE");
        albumAddPamareters.setAlbumType(AlbumType.LP);
        albumAddPamareters.setArtistId(getArtistByName("the quiett").getId());
        albumAddPamareters.setReleaseDate(DateUtil.toDate(2017, 1, 12));
        albumAddPamareters.getSongIds().add(new SongIdAndSongNo(1, getSongByName("song1").getId()));
        albumAddPamareters.getSongIds().add(new SongIdAndSongNo(2, getSongByName("song2").getId()));

        albumService.AddAlbum(albumAddPamareters);

        // act
        Album album = getAlbumByName("NEW AGE");
        String song1Name = album.getSongs().get(0).getSong().getName();
        String song2Name = album.getSongs().get(1).getSong().getName();

        // assert
        assertEquals("song1", song1Name);
        assertEquals("song2", song2Name);
        assertEquals("NEW AGE", album.getName());
        assertEquals(AlbumType.LP, album.getAlbumType());
        assertEquals("the quiett", album.getAlbumArtist().getName());
    }

    @Test
    public void albumNameChangeTest() {
        // assign
        Album album = getAlbumByName("Q Train");
        albumService.changeAlbumName(album.getId(), "Q Train2");

        em.flush();

        // act
        Album albumGet = getAlbumByName("Q Train2");

        // assert
        assertEquals("Q Train2", albumGet.getName());
    }

    private Album getAlbumByName(String name) {
        TypedQuery<Album> queryForAlbum = em.createQuery("SELECT A FROM Album A WHERE A.name = :album_name",
                Album.class);
        queryForAlbum.setParameter("album_name", name);
        Album album = queryForAlbum.getSingleResult();
        return album;
    }

    private Artist getArtistByName(String name) {
        TypedQuery<Artist> queryForArtist = em.createQuery("SELECT A FROM Artist A WHERE A.name = :aritst_name",
                Artist.class);
        queryForArtist.setParameter("aritst_name", name);
        Artist artist = queryForArtist.getSingleResult();

        return artist;
    }

    private Song getSongByName(String name) {
        TypedQuery<Song> queryForSong = em.createQuery("SELECT A FROM Song A WHERE A.name = :song_name", Song.class);
        queryForSong.setParameter("song_name", name);
        Song song = queryForSong.getSingleResult();

        return song;
    }

    @Before
    public void makeDummies() {
        Genre hiphop = new Genre("hiphop");
        em.persist(hiphop);

        Lable illionaire = new Lable();
        illionaire.changeName("Illionaire");
        em.persist(illionaire);

        Artist theQ = new Artist();
        theQ.changeName("the quiett");
        theQ.changeLable(illionaire);
        theQ.setBornDate(1996, 1, 3);
        em.persist(theQ);

        Artist swings = new Artist();
        swings.changeName("swings");
        swings.setBornDate(1987, 11, 12);
        em.persist(swings);

        Song song1 = new Song("song1", theQ, null, "");
        song1.addPersonAsSongWriter(swings, ProducerRole.Featuring);
        em.persist(song1);

        Song song2 = new Song("song2", theQ, null, "");
        em.persist(song2);

        Album album = new AlbumBuilder().artist(theQ).thisIsLPType().name("Q Train").addSong(song1, 1, false)
                .addSong(song2, 2, false).releaseDate(DateUtil.toDate(2017, 1, 1)).Build();

        em.persist(album);

        Album album2 = new AlbumBuilder().artist(theQ).thisIsLPType().name("Millionaire Poetry").Build();
        em.persist(album2);

        em.flush();
    }

    @Test
    public void viewModelTest() {
        Album album = getAlbumByName("Q Train");
        AlbumSimpleViewModel vm = albumAssembler.albumSimpleViewModel(album);

        System.out.println(vm);
        assertEquals("Q Train", vm.getAlbumName());
    }
}
