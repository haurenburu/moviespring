package com.example.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MovieController {
    MovieService movieService;

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping("/")
    public String getHome(Model model, HttpServletResponse response,@CookieValue(value = "lastVisit", defaultValue = "Never") String lastVisit, RedirectAttributes red) {
        List<Movie> movies = movieService.findAll();

        model.addAttribute("movies", movies);
        model.addAttribute("lastVisit", lastVisit);
        // update the last visit cookie
        Date d = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm:ss_dd-MM-yyyy");
        String date = df.format(d);
        Cookie c = new Cookie("lastVisit", date);
        response.addCookie(c);

        return "index";
    }
    @RequestMapping("/create")
    public String getPageCreate(Model model) {
        var movie = new Movie();
        model.addAttribute("movie", movie);
        return "create";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addMovie(@ModelAttribute @Valid Movie movie, Errors errors, RedirectAttributes ra) {
        if(errors.hasErrors()){
            return "create";

        } else {
            ra.addFlashAttribute("success", true);
            movieService.add(movie);
            return "redirect:/";
        }
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView editMovie(@PathVariable(name = "id") Long id) {
        var modelAndViews = new ModelAndView("edit");
        var movie = movieService.get(id);
        modelAndViews.addObject("movie", movie);
        return modelAndViews;
    }
    @RequestMapping("/delete/{id}")
    public String deleteMovie(@PathVariable(name = "id") Long id, RedirectAttributes ra){
        movieService.delete(id);
        ra.addFlashAttribute("success", true);
        return "redirect:/";
    }
}