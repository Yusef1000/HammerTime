package controllers;

import models.Genre;
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
        return redirect(routes.Genres.index());
    }

}
