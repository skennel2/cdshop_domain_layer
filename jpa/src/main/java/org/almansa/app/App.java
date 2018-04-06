package org.almansa.app;

import java.util.List;
import java.util.Optional;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.repository.AlbumRepository;
import org.almansa.app.repository.DummyDataMaker;
import org.almansa.app.service.LableService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = null;
		try {
			context = new AnnotationConfigApplicationContext(AppConfig.class);
			context.scan("org.almansa.app");

			makeDummies(context.getBean(DummyDataMaker.class));
			handleLableService(context.getBean(LableService.class));
			handleAlbumRepository(context.getBean(AlbumRepository.class));

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			context.close();
		}
	}

	private static void handleAlbumRepository(AlbumRepository repo) {
		Optional<Album> album = repo.findById(new Long(17));

		print(album.get().getName());

		if (album.isPresent()) {
			Album albumGet = album.get();

			String artistName = albumGet.getAlbumArtist().getName();
			String lableName = albumGet.getAlbumArtist().getLable().getName();

			print(lableName);
			print(artistName);
		} else {
			print("null");
		}
	}

	public static void makeDummies(DummyDataMaker dummyDataMaker) {
		dummyDataMaker.makeDummies();
	}

	public static void handleLableService(LableService service) {
		Lable lable = new Lable();
		lable.changeName("ambition musick");
		service.addLable(lable);

		List<Lable> lables = service.getByName("ambition musick");

		for (Lable item : lables) {
			print(item.toString());
		}
	}

	public static void print(String str) {
		System.out.println("App:");
		System.out.print("\t");
		System.out.println(str);
	}
}
