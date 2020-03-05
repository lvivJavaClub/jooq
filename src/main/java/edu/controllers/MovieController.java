package edu.controllers;

import edu.entities.Movie;
import org.jooq.DSLContext;
import org.jooq.codegen.maven.example.tables.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
public class MovieController {

    @Autowired
    private DSLContext dslContext;

    @GetMapping
    public List<Movie> movies() {
        return dslContext.selectFrom(Movies.MOVIES).fetch().into(Movie.class);
    }

}
