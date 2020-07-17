package com.example.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    MovieRepository movieRepo;

    @Autowired
    public void setMovieRepo(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    public List<Movie> findAll(){
        return movieRepo.findAll();
    }
    public void add(Movie movie) { movieRepo.save(movie); }
    public Movie get(Long id) { return movieRepo.getOne(id); }

    public void delete(Long id) {movieRepo.deleteById(id);}
}
