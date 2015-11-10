package controllers;

import play.mvc.*;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by medgardo on 11/10/15.
 */
public class Genres extends Controller {

    // List all of the Genres
    public Result index() {
        return ok("genre list");
    }

    public Result create() {
        return redirect(routes.Genres.index());
    }

}
