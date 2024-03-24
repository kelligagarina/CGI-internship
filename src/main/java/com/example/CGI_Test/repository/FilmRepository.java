package com.example.CGI_Test.repository;

import com.example.CGI_Test.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository  extends JpaRepository<Film, Long > {

}
