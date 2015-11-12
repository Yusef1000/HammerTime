package controllers;

import models.Genre;
import models.Movie;
import play.data.Form;
import play.mvc.*;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by medgardo on 11/10/15.
 */
public class Genres extends Controller {

    // List all of the Genres
    public Result index() {
        List<Genre> genres = Genre.find.all();
        return ok(views.html.genres.index.render(genres));
    }

    @Security.Authenticated(UserAuth.class)
    public Result create() {
        Genre genre = Form.form(Genre.class).bindFromRequest().get();
        genre.save();
        flash("success", "Saved new Genre: " + genre.name);
        return redirect(routes.Genres.index());
    }

    public Result show(Long id) {
        Genre genre = Genre.find.byId(id);
        if(genre == null) {
            return notFound("not found");
        } else {
            List<Movie> movies = genre.movieList;
            return ok(views.html.genres.show.render(genre, movies));
        }
    }

}
