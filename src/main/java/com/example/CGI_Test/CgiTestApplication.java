package com.example.CGI_Test;

import com.example.CGI_Test.entity.Film;

import com.example.CGI_Test.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
@SpringBootApplication
public class CgiTestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CgiTestApplication.class, args);
	}
 	@Autowired
	private FilmRepository filmRepository;

	@Override//Filme lisatatakse andmebaasi.
	public void run(String... args) throws Exception {
		Film film1 = Film.builder()
				.pealkiri("Shrek")
				.vanusepiirang(0)
				.žanr("Lastefilm")
				.kellaeg(LocalDateTime.of(2023,4,1,12,0))
				.formaat("2D")
				.keel("inglise")
				.subtiitrid("eesti")
				.build();
		Film film2 = Film.builder()
				.pealkiri("Vaesekesed")
				.vanusepiirang(14)
				.žanr("Komöödia")
				.kellaeg(LocalDateTime.of(2023,4,1,18,30))
				.formaat("2D")
				.keel("eesti")
				.subtiitrid("eesti")
				.build();

		Film film3 = Film.builder()
				.pealkiri("Madam Võrk")
				.vanusepiirang(12)
				.žanr("Märul")
				.kellaeg(LocalDateTime.of(2023,4,1,16,0))
				.formaat("3D")
				.keel("inglise")
				.subtiitrid("vene")
				.build();

		Film film4 = Film.builder()
				.pealkiri("Kung Fu Panda 4")
				.vanusepiirang(0)
				.žanr("Lastefilm")
				.kellaeg(LocalDateTime.of(2023,4,2,15,30))
				.formaat("2D")
				.keel("vene")
				.subtiitrid("puuduvad")
				.build();

		Film film5 = Film.builder()
				.pealkiri("Elu ja armastus")
				.vanusepiirang(12)
				.žanr("Draama")
				.kellaeg(LocalDateTime.of(2023,4,3,18,40))
				.formaat("2D")
				.keel("eesti")
				.subtiitrid("inglise")
				.build();
		Film film6 = Film.builder()
				.pealkiri("Düüm: teine osa")
				.vanusepiirang(12)
				.žanr("Ulmefilm")
				.kellaeg(LocalDateTime.of(2023,4,4,20,40))
				.formaat("2D")
				.keel("mitmekeelne")
				.subtiitrid("eesti")
				.build();

		Film film7 = Film.builder()
				.pealkiri("Patust puhas")
				.vanusepiirang(16)
				.žanr("Õudus")
				.kellaeg(LocalDateTime.of(2023,4,5,21,40))
				.formaat("2D")
				.keel("inglise")
				.subtiitrid("vene")
				.build();
		Film film8 = Film.builder()
				.pealkiri("Kottpea")
				.vanusepiirang(12)
				.žanr("Õudus")
				.kellaeg(LocalDateTime.of(2023,4,6,22,10))
				.formaat("2D")
				.keel("inglise")
				.subtiitrid("vene")
				.build();
		Film film9 = Film.builder()
				.pealkiri("Koer Arthur")
				.vanusepiirang(12)
				.žanr("Seiklus")
				.kellaeg(LocalDateTime.of(2023,4,7,16,50))
				.formaat("2D")
				.keel("inglise")
				.subtiitrid("eesti")
				.build();
		filmRepository.save(film1);
		filmRepository.save(film2);
		filmRepository.save(film3);
		filmRepository.save(film4);
		filmRepository.save(film5);
		filmRepository.save(film6);
		filmRepository.save(film7);
		filmRepository.save(film8);
		filmRepository.save(film9);

	}



}
