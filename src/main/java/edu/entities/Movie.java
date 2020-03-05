package edu.entities;

import java.time.LocalDate;

public class Movie {
    public Integer id;
    public String name;
    public LocalDate year;
    public String director;

    public Movie(Integer id, String name, LocalDate year, String director) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.director = director;
    }

    public String getName() {
        return name;
    }

    public LocalDate getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }
}
