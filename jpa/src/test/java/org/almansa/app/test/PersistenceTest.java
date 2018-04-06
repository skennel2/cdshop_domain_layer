package org.almansa.app.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.almansa.app.AppConfig;
import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.AlbumType;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.domain.album.Song;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
@Transactional // 이게 없으면 @PersistenceContext 사용에 있어서 익셉션이 발생한다.
public class PersistenceTest {

	@PersistenceContext
	private EntityManager em;
	
	@Test
	public void persistAndFindTest() 	
	{
		Album album = new Album();	
		album.setName("illmatic");		
		album.setAlbumType(AlbumType.LP);
		
		em.persist(album);
		em.flush();
		
		Album albumFind = em.find(Album.class, album.getId());
		
		boolean isEquals = album.equals(albumFind);		
		assertEquals(true, isEquals);		
		assertEquals("illmatic", albumFind.getName());		
	}	
	
	@Test
	public void persistAndFindAndRelationTest() {		
		Lable illionaire = new Lable();
		illionaire.changeName("Illionaire");
		em.persist(illionaire);			
		
		Artist theQ = new Artist();
		theQ.changeName("the quiett");
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
		
		List<Song> songList = new ArrayList<Song>();
		songList.add(song1);
		songList.add(song2);
		
		Album album = new Album();
		album.setAlbumArtist(theQ);
		album.setAlbumType(AlbumType.LP);
		album.setName("Q Train");
		album.setSongs(songList);
		em.persist(album);

		em.flush();		
		
		Album albumFind = em.find(Album.class, album.getId());		
		assertEquals(true, album.equals(albumFind));	
		
		Artist artistGet = albumFind.getAlbumArtist();
		assertEquals("the quiett", artistGet.getName());
	
		Lable lableGet = artistGet.getLable();
		assertEquals("Illionaire", lableGet.getName());
		
		List<Song> songs = album.getSongs();
		assertEquals(2, songs.size());		
	}
}