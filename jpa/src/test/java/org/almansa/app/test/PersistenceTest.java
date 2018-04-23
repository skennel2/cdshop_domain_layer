package org.almansa.app.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.almansa.app.AppConfig;
import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.AlbumBuilder;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.domain.album.Song;
import org.almansa.app.domain.merchandise.AlbumMerchandise;
import org.almansa.app.domain.order.PurchaseOrder;
import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.domain.value.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
public class PersistenceTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void joinQueryTest() {
        TypedQuery<Artist> query = em.createQuery(
                "SELECT B FROM Album A INNER JOIN A.albumArtist B WHERE B.name = :aritst_name", Artist.class);

        query.setParameter("aritst_name", "the quiett");
        Artist artist = query.getSingleResult();

        assertEquals("the quiett", artist.getName());
    }

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

        Song song1 = new Song("song1", theQ, null, "");
        em.persist(song1);

        Song song2 = new Song("song2", theQ, null, "");
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
    public void OrderPersistAndDomainTest() {
        TypedQuery<Album> q = em.createQuery("SELECT A FROM Album A WHERE A.name = :name", Album.class);
        q.setParameter("name", "Q Train");

        Album album = q.getSingleResult();
        AlbumMerchandise albumMd1 = new AlbumMerchandise(new Long(200), new Money(25000), album);
        em.persist(albumMd1);

        q.setParameter("name", "Millionaire Poetry");

        Album album2 = q.getSingleResult();
        AlbumMerchandise albumMd2 = new AlbumMerchandise(new Long(200), new Money(10000), album2);
        em.persist(albumMd2);

        ApplicationUser user = new ApplicationUser("skennel", "skennel", "1234");

        if (album != null) {
            PurchaseOrder newOrder = new PurchaseOrder(user, null, new Date());
            newOrder.addOrderLine(albumMd1, 10);
            em.persist(newOrder);

            assertEquals(newOrder.calculateTotalPrice(), new Money(250000));

            newOrder.addOrderLine(albumMd2, 5);

            assertEquals(newOrder.calculateTotalPrice(), new Money(300000));
        } else {
            fail("album not found");
        }
    }

    @Test
    public void persistAndFindTest() {
        Album album = new AlbumBuilder().name("illmatic").thisIsLPType().Build();

        em.persist(album);
        em.flush();

        Album albumFind = em.find(Album.class, album.getId());

        boolean isEquals = album.equals(albumFind);
        assertEquals(true, isEquals);
        assertEquals("illmatic", albumFind.getName());
    }
}