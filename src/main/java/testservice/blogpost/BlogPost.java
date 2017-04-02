package testservice.blogpost;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/*
 * The blog post object.
 * Blog post objects will be stored in the database.
 */

@Entity
public class BlogPost {

    @Id
    private String id;
    private String title;
    private String comment;
    private Date date;

    public BlogPost() {

    }

    public BlogPost(String id, String title, String comment) {
        this.id = id;
        this.title = title;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
