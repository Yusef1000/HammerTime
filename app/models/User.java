package models;

import com.avaje.ebean.Model;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.Constraint;

/**
 * Created by medgardo on 11/10/15.
 */
@Table(name="users")
@Entity
public class User extends Model {
    @Id
    public Long id;

    @Constraints.Required
    @Column(unique=true)
    public String username;


    public String password_hash;

    // A finder object for easier querying
    public static Finder<Long, User> find = new Finder<Long, User>(User.class);


    // NOT FOR PRODUCTION - must ensure this is a valid user first. I have not done that.
    public boolean authenticate(String password) {
        return BCrypt.checkpw(password, this.password_hash);
    }

    public static User createNewUser(String username, String password) {
        if(password == null || username == null || password.length() < 8) {
            return null;
        }

        // Create a password hash
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User();
        user.username = username;
        user.password_hash = passwordHash;

        return user;
    }




}
