package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by medgardo on 11/10/15.
 */
@Entity
public class Genre extends Model {
    @Id
    public Long id;

    @Constraints.Required
    public String name;

}
