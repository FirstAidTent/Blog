package testservice.blogpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;


//    private List<BlogPost> blogPosts = new ArrayList<>(Arrays.asList(
//            new BlogPost("1", "number1", "This is number 1"),
//            new BlogPost("2", "number2", "This is number 2"),
//            new BlogPost("3", "number3", "This is number 3")
//    ));

    public List<BlogPost> getAllBlogPosts() {
//        return blogPosts;
        List<BlogPost> blogPosts = new ArrayList<>();
        blogPostRepository.findAll().forEach(blogPosts::add);
        return blogPosts;
    }

    public BlogPost getBlogPost(String id) {
//        return blogPosts.stream().filter(t -> t.getId().equals(id)).findFirst().get();
        return blogPostRepository.findOne(id);
    }


    public boolean addBlogPost(BlogPost blogPost) {
//        return blogPosts.add(blogPost);
        if (blogPostRepository.findOne(blogPost.getId()) == null) {
            blogPostRepository.save(blogPost);
            return true;
        }
        return false;
    }

    public boolean updateBlogPost(BlogPost blogPost, String id) {
//        for (int i = 0; i < blogPosts.size(); i++) {
//            BlogPost t = blogPosts.get(i);
//            if (t.getId().equals(id)) {
//                blogPosts.set(i, blogPost);
//                return true;
//            }
//        }
//        return false;
        if (blogPostRepository.findOne(id) != null) {
            blogPostRepository.save(blogPost);
            return true;
        }
        return false;
    }

    public boolean deleteBlogPost(String id) {
//        return blogPosts.removeIf(t -> t.getId().equals(id));
        if (blogPostRepository.findOne(id) != null) {
            blogPostRepository.delete(id);
            return true;
        }
         return false;
    }
}
