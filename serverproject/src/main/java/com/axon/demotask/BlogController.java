package com.axon.demotask;

import com.axon.demotask.database.model.Blogpost;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BlogController extends HandlerInterceptorAdapter {
    @Autowired
    private BlogpostRepository blogpostRepository;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS");
        return true;
    }

    /**
     * Gets the list of all blog posts.
     *
     * @param own filter option to get only own posts
     * @return
     */
    @GetMapping("/blogposts")
    public List<ExistingBlogPostBean> getBlogPosts(@RequestParam(value = "own", defaultValue = "false") Boolean own, Principal principal) {
        System.out.println("getBlogPosts method called. own=" + own + ", name=" + principal.getName());
        if (own) {
            return this.blogpostRepository.getAllOwnBlogposts(principal.getName());
        } else {
            return this.blogpostRepository.getAllBlogposts();
        }
    }

    /**
     * Gets a particular blog post by id.
     *
     * @param blogpostid id of a blog post
     * @return
     */
    @GetMapping("/blogposts/{blogpostid}")
    public ExistingBlogPostBean getBlogPostByID(@PathVariable String blogpostid) {
        System.out.println("getBlogPostByID method called");
        return blogpostRepository.getBlogpostByID(blogpostid);
    }

    /**
     * Creates a new blog post.
     *
     * @param newBlogPost
     * @return blog post populated with ID
     */
    @PostMapping("/blogposts")
    public ExistingBlogPostBean createOwnBlogPost(@RequestBody BlogPostDataBean newBlogPost, Principal principal) {
        System.out.println("BlogPostDataBean method called");
        return this.blogpostRepository.createOwnBlogpost(newBlogPost, principal.getName());
    }

    /**
     * Updates an own blog post.
     */
    @RequestMapping(value = "/blogposts/{blogpostid}", method = RequestMethod.PUT)
    public void updateOwnBlogPost(@RequestBody BlogPostDataBean newBlogPost,@PathVariable String blogpostid, Principal principal) {
        System.out.println("updateOwnBlogPost method called");
        ExistingBlogPostBean existingBlogPostBean = blogpostRepository.getBlogpostByID(blogpostid);
        if (existingBlogPostBean == null) {
            throw new ResourceNotFoundException();
        }
        if (!existingBlogPostBean.getAuthorID().equals(principal.getName())) {
            throw new RuntimeException("It is forbidden to modify blog posts of another person.");
        }
        this.blogpostRepository.updateOwnBlogpost(newBlogPost, blogpostid);
    }

    /**
     * Deletes an own blog post.
     */
    @RequestMapping(value = "/blogposts/{blogpostid}", method = RequestMethod.DELETE)
    public void deleteOwnBlogPost(@PathVariable String blogpostid, Principal principal) {
        System.out.println("deleteOwnBlogPost method called");
        ExistingBlogPostBean existingBlogPostBean = blogpostRepository.getBlogpostByID(blogpostid);
        if (existingBlogPostBean == null) {
            throw new ResourceNotFoundException();
        }
        if (!existingBlogPostBean.getAuthorID().equals(principal.getName())) {
            throw new RuntimeException("It is forbidden to modify blog posts of another person.");
        }

        this.blogpostRepository.deleteOwnBlogpost(blogpostid);
    }
}
