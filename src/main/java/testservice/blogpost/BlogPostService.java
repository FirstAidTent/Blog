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
        return blogPostRepository.findById(id).get();
    }


    public boolean addBlogPost(BlogPost blogPost) {
        if (!blogPostRepository.findById(blogPost.getId()).isPresent()) {
            blogPostRepository.save(blogPost);
            return true;
        }
        return false;
    }

    public BlogPost updateBlogPost(BlogPost blogPost, String id) {
        if (blogPostRepository.findById(id).isPresent()) {
            return blogPostRepository.save(blogPost);
        }
        return null;
    }

    public boolean deleteBlogPost(String id) {
        if (blogPostRepository.findById(id).isPresent()) {
            blogPostRepository.deleteById(id);
            return true;
        }
         return false;
    }
}
