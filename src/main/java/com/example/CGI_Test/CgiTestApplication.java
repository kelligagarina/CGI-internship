package com.example.CGI_Test;

import com.example.CGI_Test.entity.Film;

import com.example.CGI_Test.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class CgiTestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CgiTestApplication.class, args);
	}
 	@Autowired
	private FilmRepository filmRepository;
	@Override
	public void run(String... args) throws Exception {
		Film film1 = Film.builder()
				.pealkiri("Shrek")
				.vanusepiirang(0)
				.žanr("Lastefilm")
				.kellaeg(LocalDateTime.of(2023,4,1,12,0))
				.build();
		Film film2 = Film.builder()
				.pealkiri("Vaesekesed")
				.vanusepiirang(14)
				.žanr("Komöödia")
				.kellaeg(LocalDateTime.of(2023,4,1,18,30))
				.build();

		Film film3 = Film.builder()
				.pealkiri("Madam Võrk")
				.vanusepiirang(12)
				.žanr("Märul")
				.kellaeg(LocalDateTime.of(2023,4,1,16,0))
				.build();

		filmRepository.save(film1);
		filmRepository.save(film2);
		filmRepository.save(film3);

	}


}
