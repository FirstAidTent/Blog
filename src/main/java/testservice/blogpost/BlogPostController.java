package testservice.blogpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/*
 * This is the controller that controls the incoming requests.
 * The implementation of the methods are done in the BlogPostService.java file.
 * Depending on what links are given, different methods are run.
 * All the returned data will be in JSON.
 */

@RestController
public class BlogPostController {

    private final AtomicLong postCounter = new AtomicLong();

    @Autowired
    private BlogPostService blogPostService;

    // When a "GET" request method is received, all data from the database will be returned.
    @RequestMapping(value = "/blogposts", method = RequestMethod.GET)
    public List<BlogPost> getAllblogposts() {
        return blogPostService.getAllBlogPosts();
    }

    // Returns a blog post depending on the id provided.
    @RequestMapping("/blogposts/{id}")
    public BlogPost getBlogPost(@PathVariable String id) {
        BlogPost bp = blogPostService.getBlogPost(id);
        if (bp != null) {
            return bp;
        }
        return new BlogPost("null", "null", "null");
    }

    // When a "POST" request method is received, there also needs to be a title and blog text (comment)
    // provided with the request. This is stored in the database and the all the data from the database is then returned.
    @RequestMapping(value = "/blogposts", method = RequestMethod.POST)
    public List<BlogPost> addBlogPost(@RequestParam(value = "title", defaultValue = "Blog Post") String title,
                         @RequestParam(value = "comment", defaultValue = "Just Another Comment") String comment) {
        BlogPost t = new BlogPost(Long.toString(postCounter.incrementAndGet()), title, comment);
        blogPostService.addBlogPost(t);
        return blogPostService.getAllBlogPosts();
    }

    // The "PUT" request method is used to edit a post.
    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.PUT)
    public BlogPost updateBlogPost(@RequestBody BlogPost blogPost, @PathVariable String id) {
        return blogPostService.updateBlogPost(blogPost, id);
    }

    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.DELETE)
    public List<BlogPost> deleteBlogPost(@PathVariable String id) {
        blogPostService.deleteBlogPost(id);
        return blogPostService.getAllBlogPosts();
    }
}
