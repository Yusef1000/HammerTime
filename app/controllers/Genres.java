package controllers;

import models.Genre;
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

    public Result create() {
        Genre genre = Form.form(Genre.class).bindFromRequest().get();
        flash("success", "received data: " + genre.name);
        return redirect(routes.Genres.index());
    }

}
