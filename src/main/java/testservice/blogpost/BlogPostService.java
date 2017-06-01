package testservice.blogpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * This is where all the method implementations are done.
 * They are then used in the blog post controller.
 */

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;


    public List<BlogPost> getAllBlogPosts() {
        List<BlogPost> blogPosts = new ArrayList<>();
        blogPostRepository.findAll().forEach(blogPosts::add);
        return blogPosts;
    }

    public BlogPost getBlogPost(String id) {
        return blogPostRepository.findOne(id);
    }


    public boolean addBlogPost(BlogPost blogPost) {
        if (blogPostRepository.findOne(blogPost.getId()) == null) {
            blogPostRepository.save(blogPost);
            return true;
        }
        return false;
    }

    public BlogPost updateBlogPost(BlogPost blogPost, String id) {
        if (blogPostRepository.findOne(id) != null) {
            return blogPostRepository.save(blogPost);
        }
        return null;
    }

    public boolean deleteBlogPost(String id) {
        if (blogPostRepository.findOne(id) != null) {
            blogPostRepository.delete(id);
            return true;
        }
         return false;
    }
}
