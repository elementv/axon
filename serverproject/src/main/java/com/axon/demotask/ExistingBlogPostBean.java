package com.axon.demotask;

/**
 * Bean class that represents an existing blog post. It defines the blog post ID and author ID.
 */
public class ExistingBlogPostBean extends BlogPostDataBean {
    /**
     * Unique ID of the post.
     */
    private String postID;

    /**
     * ID of the user who created the blog post.
     */
    private String authorID;

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }
}
