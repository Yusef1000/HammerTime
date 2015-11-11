package controllers;

import models.Genre;
import models.Movie;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.data.Form.form;

/**
 * Created by medgardo on 11/10/15.
 */
public class Movies extends Controller {

    public Result index() {
        List<Movie> movies = Movie.find.all();
        List<Genre> genres = Genre.find.all();
        return ok(views.html.movies.index.render(movies, genres));
    }

    public Result create() {
        Form<Movie> movieForm = form(Movie.class).bindFromRequest();
        String genre_id = movieForm.data().get("genre_id");

        Genre genre = Genre.find.byId(Long.parseLong(genre_id));
        if(genre == null) {
            flash("error", "Invalid Genre: " + genre_id + " Try again.");
            return redirect(routes.Movies.index());
        }

        Movie movie = movieForm.get();
        movie.genre = genre;
        movie.save();
        flash("success", "Saved new Movie: " + movie.title);
        return redirect(routes.Movies.index());
    }



}
