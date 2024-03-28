package com.example.CGI_Test.controller;

import com.example.CGI_Test.entity.Film;
import com.example.CGI_Test.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class SoovitusController {

    @Autowired
    private FilmRepository filmRepository;

    private List<Film> vaadatudFilmid = Arrays.asList(//Käsitsi sisestatud filmiajalugu.
            new Film(1, "Tere", LocalDateTime.of(2023,4,1,18,30), 12, "Draama", "eesti", "2D", "puuduvad",0.0),
            new Film(1, "Tere", LocalDateTime.of(2023,4,1,18,30), 12, "Märul", "eesti", "2D", "puuduvad",0.0),
            new Film(1, "Tere", LocalDateTime.of(2023,4,1,18,30), 12, "Märul", "eesti", "2D", "puuduvad",0.0),
            new Film(1, "Tere", LocalDateTime.of(2023,4,1,18,30), 12, "Märul", "eesti", "2D", "puuduvad",0.0),
            new Film(1, "Tere", LocalDateTime.of(2023,4,1,18,30), 12, "Draama", "eesti", "2D", "puuduvad",0.0),
            new Film(1, "Tere", LocalDateTime.of(2023,4,1,18,30), 12, "Komöödia", "eesti", "2D", "puuduvad",0.0)



    );
    @GetMapping("/soovitaFilme")
    public List<Film> soovitaFilme() {//Filmide soovitamise algoritm. Soovitamine toimub žanride järgi.
        List<Film> filmid = filmRepository.findAll();
        List<Film> soovitatudFilmid = new ArrayList<>();
        int filmideKoguarv = vaadatudFilmid.size();
        Map<String, Double> zanrideOsakaalud = new HashMap<>();//Loetleb mitu ja mis žanri esines vaatamisajaloos.

        for (Film film : vaadatudFilmid) {
            String genre = film.getŽanr();
            zanrideOsakaalud.put(genre, zanrideOsakaalud.getOrDefault(genre, 0.0) + 1);
        }
        for (String žanr : zanrideOsakaalud.keySet())//Vahetab esinemiste arvu osakaaluga vaadatutest filmides.
            zanrideOsakaalud.put(žanr,(zanrideOsakaalud.get(žanr) / filmideKoguarv) * 100);

        for (Film film : filmid) {
            if (zanrideOsakaalud.containsKey(film.getŽanr())){//kui nädala kavas on filme sama žanriga, mis esines vaatamisajaloos - pannakse neid listi.
                film.setSoovitus(zanrideOsakaalud.get(film.getŽanr()));
                soovitatudFilmid.add(film);
        }}
        Collections.sort(soovitatudFilmid, new Comparator<Film>() {//Sorteerimine kahanevas järjekorras.
            @Override
            public int compare(Film film1, Film film2) {
                return Double.compare(film2.getSoovitus(), film1.getSoovitus());
            }
        });
        return soovitatudFilmid;
    }
}
