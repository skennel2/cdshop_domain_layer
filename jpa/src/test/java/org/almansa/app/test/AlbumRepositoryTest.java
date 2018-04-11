package org.almansa.app.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.almansa.app.AppConfig;
import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.AlbumBuilder;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.domain.album.Song;
import org.almansa.app.repository.AlbumRepository;
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
public class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository albumRepo;

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

        Album album = new AlbumBuilder().artist(theQ).thisIsLPType().name("Q Train").Build();
        album.addSong(song1, 1, false);
        album.addSong(song2, 2, false);
        em.persist(album);

        Album album2 = new AlbumBuilder().artist(theQ).thisIsLPType().name("Millionaire Poetry").Build();
        em.persist(album2);

        em.flush();
    }

    @Test
    public void findByArtistNameTest() {
        String artistName = "the quiett";

        List<Album> list = albumRepo.findByArtistName(artistName);

        for (Album album : list) {
            assertEquals(artistName, album.getAlbumArtist().getName());
        }
    }

    @Test
    public void findByArtistNameNotExistTest() {
        String artistName = "Not Exists";

        List<Album> list = albumRepo.findByArtistName(artistName);

        assertEquals(0, list.size());
    }

    @Test
    public void findByArtistNameNullTest() {
        String artistName = null;

        List<Album> list = albumRepo.findByArtistName(artistName);

        assertEquals(0, list.size());
    }

    @Test
    public void findByAlbumNameTest() {
        String albumName = "Millionaire Poetry";

        List<Album> list = albumRepo.findByName(albumName);

        for (Album album : list) {
            assertEquals(albumName, album.getName());
        }
    }

    @Test
    public void findByAlbumNameNotExistTest() {
        String albumName = "Not Exists";

        List<Album> list = albumRepo.findByName(albumName);

        for (Album album : list) {
            assertEquals(albumName, album.getName());
        }
    }

    @Test
    public void findByAlbumNameNullTest() {
        String albumName = null;

        List<Album> list = albumRepo.findByName(albumName);

        for (Album album : list) {
            assertEquals(albumName, album.getName());
        }
    }

    @Test
    public void setAlbumNameUpdateTest() {
        String albumName = "Millionaire Poetry";
        List<Album> list = albumRepo.findByName(albumName);

        for (Album album : list) {
            album.changeName("Millionaire Poetry2");
        }

        em.flush();

        albumName = "Millionaire Poetry2";
        list = albumRepo.findByName(albumName);

        for (Album album : list) {
            assertEquals(albumName, album.getName());
        }
    }

    @Test
    public void saveTest() {
        Album newAlbum = new AlbumBuilder().artist(null).name("newAlbum").releaseDate(DateUtil.toDate(2017, 12, 1))
                .Build();
        albumRepo.save(newAlbum);
        em.flush();

        List<Album> list = albumRepo.findByName("newAlbum");
        System.out.println(list.get(0).getName());
        assertEquals(1, list.size());
    }
}