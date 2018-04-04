package org.almansa.app.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.almansa.app.domain.AgencyCompany;
import org.almansa.app.domain.Album;
import org.almansa.app.domain.AlbumType;
import org.almansa.app.domain.Artist;
import org.almansa.app.domain.Producer;
import org.almansa.app.domain.Song;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class TestRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void test() {
		AgencyCompany jm = new AgencyCompany();
		jm.changeName("just musick");
		em.persist(jm);
		
		Producer nochang = new Producer();
		nochang.setName("nochang");
		nochang.setAgencyCompany(jm);
		em.persist(nochang);
		
		Producer chachamalon = new Producer();
		chachamalon.setName("chachamalon");
		em.persist(chachamalon);		
		
		Artist swings = new Artist();
		swings.changeName("swings");
		swings.changeAgencyCompany(jm);
		em.persist(swings);
		
		Artist theQ = new Artist();
		theQ.changeName("the quiett");
		theQ.changeAgencyCompany(null);
		em.persist(theQ);	

		Artist kidMilli = new Artist();
		kidMilli.changeName("kidmilli");
		kidMilli.changeAgencyCompany(null);
		em.persist(kidMilli);	
		
		Song song = new Song();
		song.setName("holy");
		song.setOwnerArtist(swings);
		song.setProducer(nochang);
		em.persist(song);
		
		Song song2 = new Song();
		song2.setName("shit is real");
		song2.setOwnerArtist(swings);
		song2.setProducer(chachamalon); 
		List<Artist> list =  new ArrayList<Artist>();
		list.add(theQ);
		list.add(kidMilli);
		song2.setArtists(list);
		em.persist(song2);

		Song song3 = new Song();
		song3.setName("intro");
		song3.setOwnerArtist(swings);
		song3.setProducer(chachamalon); 
		em.persist(song3);		
		
		Album album = new Album();
		album.setAlbumArtist(swings);
		album.setName("upgrade3");
		List<Song> list2 =  new ArrayList<Song>();
		list2.add(song);
		list2.add(song2);		
		album.setSongs(list2);
		album.setReleaseDate(new Date());
		album.setAlbumType(AlbumType.LP);
		em.persist(album);	
	}
	
	public Song getSong(Long id) {
		return em.find(Song.class, id);
	}
	
}
