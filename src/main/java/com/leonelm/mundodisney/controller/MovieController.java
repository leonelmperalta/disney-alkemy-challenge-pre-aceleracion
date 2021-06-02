package com.leonelm.mundodisney.controller;

import com.leonelm.mundodisney.model.Movie;
import com.leonelm.mundodisney.service.MovieService;
import com.leonelm.mundodisney.util.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDTO> getMovies(
            @RequestParam(value="nombre", required = false) String title,
            @RequestParam(value="idGenero", required = false) Long genreId,
            @RequestParam(value="order", required = false) String order
    ){
        if(title != null && title.length() > 0){
           return movieService.getMoviesByTitle(title);
        } else if (genreId != null) {
            return movieService.getMoviesByGenreId(genreId);
        }
        return movieService.getMovies();
    }

    @GetMapping(value="/{id}")
    public Movie getMovieDetail(@PathVariable Long id){
        return movieService.getMovieDetail(id);
    }

    @PostMapping
    public void createMovie(@RequestBody Movie movie){
        movieService.createMovie(movie);
    }

    @PutMapping(value = "/{id}")
    public void updateMovie(@PathVariable Long id,@RequestBody Movie movie){
        movieService.updateMovie(id, movie);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
    }

    @PutMapping(value = "/setGenre")
    public void setGenre(@RequestParam("movieId") Long id, @RequestParam("genreId") Long genreId){
        movieService.setGenre(id,genreId);
    }

}
