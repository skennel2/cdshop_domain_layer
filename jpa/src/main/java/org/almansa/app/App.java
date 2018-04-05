package org.almansa.app;

import java.util.List;

import org.almansa.app.domain.Artist;
import org.almansa.app.domain.Song;
import org.almansa.app.repository.TestRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.scan("org.almansa.app");

        TestRepository repo = context.getBean(TestRepository.class);
    
        repo.test();
        Song song = repo.getSong(new Long(11));
        
        List<Artist> list = song.getArtists();
        
        for (Artist artist : list) {
        	System.out.println(artist.toString());
		}
        
        System.out.println("-------------------------");
        System.out.println(song.toString());
        
        context.close();
    }
}
