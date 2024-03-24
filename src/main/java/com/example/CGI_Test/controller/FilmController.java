package com.example.CGI_Test.controller;

import com.example.CGI_Test.entity.Film;
import com.example.CGI_Test.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;
    @GetMapping("/kinokava")
    public List<Film> fetchFilmid(){
        return filmRepository.findAll();
    }

}
