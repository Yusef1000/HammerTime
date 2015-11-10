package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by medgardo on 11/10/15.
 */
@Entity
public class Movie extends Model {
    @Id
    public Long id;

    @Constraints.Required
    public String title;

    public String summary;

    public String url;

    @ManyToOne
    public Genre genre;

    // A finder object for easier querying
    public static Finder<Long, Movie> find = new Finder<Long, Movie>(Movie.class);
}
