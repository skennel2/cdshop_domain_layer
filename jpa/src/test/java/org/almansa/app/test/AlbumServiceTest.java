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
import org.almansa.app.domain.album.CategoryTag;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.domain.album.Song;
import org.almansa.app.domain.dto.AlbumSimpleViewModel;
import org.almansa.app.domain.dto.SongIdAndSongNo;
import org.almansa.app.service.AlbumAddParameterModel;
import org.almansa.app.service.AlbumService;
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
    private AlbumService albumService;

    @PersistenceContext
    private EntityManager em;

    @Before
    public void makeDummies() {
        Lable illionaire = new Lable();
        illionaire.changeName("Illionaire");
        em.persist(illionaire);

        Artist theQ = new Artist();
        theQ.changeName("the quiett");
        theQ.changeLable(illionaire);
        theQ.setBornDate(1996, 1, 3);
        em.persist(theQ);

        Song song1 = new Song();
        song1.changeName("song1");
        song1.setOwnerArtist(theQ);
        em.persist(song1);

        Song song2 = new Song();
        song2.changeName("song2");
        song2.setOwnerArtist(theQ);
        em.persist(song2);

        Album album = new AlbumBuilder().artist(theQ).thisIsLPType().name("Q Train")
                .releaseDate(DateUtil.toDate(2017, 1, 1)).Build();

        album.addSong(song1, 1, false); // TODO Builder
        album.addSong(song2, 2, false);
        em.persist(album);

        Album album2 = new AlbumBuilder().artist(theQ).thisIsLPType().name("Millionaire Poetry").Build();
        em.persist(album2);

        em.flush();
    }

    @Test
    public void addAlbumTest() {
        // save
        AlbumAddParameterModel albumAddPamareters = new AlbumAddParameterModel();
        albumAddPamareters.setAlbumName("NEW AGE");
        albumAddPamareters.setAlbumType(AlbumType.LP);
        albumAddPamareters.setArtistId(getArtistByName("the quiett").getId());
        albumAddPamareters.setReleaseDate(DateUtil.toDate(2017, 1, 12));
        albumAddPamareters.getSongIds().add(new SongIdAndSongNo(1, getSongByName("song1").getId()));
        albumAddPamareters.getSongIds().add(new SongIdAndSongNo(2, getSongByName("song2").getId()));
        albumAddPamareters.getTag().add("category1");
        albumAddPamareters.getTag().add("category2");

        albumService.AddAlbum(albumAddPamareters);

        // get
        Album album = getAlbumByName("NEW AGE");
        String song1Name = album.getSongs().get(0).getSong().getName();
        String song2Name = album.getSongs().get(1).getSong().getName();

        // assert
        assertEquals("song1", song1Name);
        assertEquals("song2", song2Name);
        assertEquals("NEW AGE", album.getName());
        assertEquals(AlbumType.LP, album.getAlbumType());
        assertEquals("the quiett", album.getAlbumArtist().getName());
        assertEquals("category1", album.getTags().get(0).getName());
    }

    @Test
    public void viewModelTest() {
        Album album = getAlbumByName("Q Train");
        AlbumSimpleViewModel vm = new AlbumSimpleViewModel(album);

        System.out.println(vm);
        assertEquals("Q Train", vm.getAlbumName());
    }

    @Test
    public void albumNameChangeTest() {
        Album album = getAlbumByName("Q Train");
        albumService.changeAlbumName(album.getId(), "Q Train2");

        em.flush();

        Album albumGet = getAlbumByName("Q Train2");
        assertEquals("Q Train2", albumGet.getName());
    }

    @Test
    public void albumTagAddTest() {
        Album album = getAlbumByName("Q Train");

        List<String> newTags = new ArrayList<String>();
        newTags.add("c1");
        newTags.add("c2");
        albumService.addTagToAlbum(album.getId(), newTags);

        em.flush();

        Album albumGet = getAlbumByName("Q Train");
        for (CategoryTag tag : albumGet.getTags()) {
            System.out.println(tag);
        }
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
}
