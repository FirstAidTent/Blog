package testservice.blogpost;

import org.springframework.data.repository.CrudRepository;

/*
 * An apache derby database is used to store the data for now.
 * This is just a test database and will be reset every time the service is shut down.
 * Usually, you need to implement the methods that send queries to the database, but Java Spring does it
 * automatically for you.
 */

public interface BlogPostRepository extends CrudRepository<BlogPost, String> {

}
