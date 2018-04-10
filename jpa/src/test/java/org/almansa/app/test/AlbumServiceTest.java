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
import org.almansa.app.domain.album.AlbumType;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.domain.album.Song;
import org.almansa.app.domain.album.SongInAlbum;
import org.almansa.app.service.AlbumAddModel;
import org.almansa.app.service.AlbumService;
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
        illionaire.setName("Illionaire");
        em.persist(illionaire);

        Artist theQ = new Artist();
        theQ.setName("the quiett");
        theQ.changeLable(illionaire);
        theQ.setBornDate(1996, 1, 3);
        em.persist(theQ);

        Song song1 = new Song();
        song1.setName("song1");
        song1.setOwnerArtist(theQ);
        em.persist(song1);

        Song song2 = new Song();
        song2.setName("song2");
        song2.setOwnerArtist(theQ);
        em.persist(song2);

        Album album = new Album();
        album.setAlbumArtist(theQ);
        album.setAlbumType(AlbumType.LP);
        album.setName("Q Train");

        List<SongInAlbum> songList = new ArrayList<SongInAlbum>();
        songList.add(new SongInAlbum(album, song1, 1, false));
        songList.add(new SongInAlbum(album, song2, 2, false));
        album.setSongs(songList);
        em.persist(album);

        Album album2 = new Album();
        album2.setAlbumArtist(theQ);
        album2.setAlbumType(AlbumType.LP);
        album2.setName("Millionaire Poetry");
        em.persist(album2);

        em.flush();
    }
    
    @Test
    public void addAlbumTest() {
        TypedQuery<Artist> queryForArtist = em.createQuery(
                "SELECT B FROM Album A INNER JOIN A.albumArtist B WHERE B.name = :aritst_name", Artist.class);
        queryForArtist.setParameter("aritst_name", "the quiett");
        Artist artist = queryForArtist.getSingleResult();
        
        AlbumAddModel albumAddModel = new AlbumAddModel();
        albumAddModel.setAlbumName("NEW AGE");
        albumAddModel.setAlbumType(AlbumType.LP);
        albumAddModel.setArtistId(artist.getId());
            
        albumService.AddAlbum(albumAddModel);
        
        TypedQuery<Album> queryForAlbum = em.createQuery(
                "SELECT A FROM Album A WHERE A.name = :album_name", Album.class);
        queryForAlbum.setParameter("album_name", "NEW AGE");
        Album album = queryForAlbum.getSingleResult();
        
        assertEquals("NEW AGE", album.getName());
        assertEquals(AlbumType.LP, album.getAlbumType());
        assertEquals("the quiett", album.getAlbumArtist().getName());
    }
}
