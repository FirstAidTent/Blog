package testservice.blogpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class BlogPostController {

    private final AtomicLong postCounter = new AtomicLong();

    @Autowired
    private BlogPostService blogPostService;

    @RequestMapping(value = "/blogposts", method = RequestMethod.GET)
    public List<BlogPost> getAllblogposts() {
        return blogPostService.getAllBlogPosts();
    }

    @RequestMapping("/blogposts/{id}")
    public BlogPost getBlogPost(@PathVariable String id) {
        BlogPost bp = blogPostService.getBlogPost(id);
        if (bp != null) {
            return bp;
        }
        return new BlogPost("null", "null", "null");
    }

    @RequestMapping(value = "/blogposts", method = RequestMethod.POST)
    public String addBlogPost(@RequestParam(value = "name", defaultValue = "Just Another Person") String name,
                         @RequestParam(value = "comment", defaultValue = "Just Another Comment") String comment) {
        BlogPost t = new BlogPost(Long.toString(postCounter.incrementAndGet()), name, comment);
        if (blogPostService.addBlogPost(t)) {
            return "Successfully posted your blog post";
        }
        return "Could not add blog post because a blog post with the id \"" + t.getId() + "\" already exists.";
    }

    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.PUT)
    public String updateBlogPost(@RequestBody BlogPost blogPost, @PathVariable String id) {
        if (blogPostService.updateBlogPost(blogPost, id)) {
            return "Successfully updated blog post with id \"" + id + "\"";
        }
        return "Could not update blog post because no blog post with id \"" + id + "\" exists.";
    }

    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.DELETE)
    public String deleteBlogPost(@PathVariable String id) {
        if (blogPostService.deleteBlogPost(id)) {
            return "Successfully deleted blog post with id \"" + id + "\"";
        } else {
            return "No blog post got deleted because no blog post with id \"" + id + "\" exists.";
        }
    }
}
