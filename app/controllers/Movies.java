package controllers;

import models.Genre;
import models.Movie;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

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
        Movie movie = Form.form(Movie.class).bindFromRequest().get();
        movie.save();
        flash("success", "Saved new Movie: " + movie.title);
        return redirect(routes.Movies.index());
    }



}
