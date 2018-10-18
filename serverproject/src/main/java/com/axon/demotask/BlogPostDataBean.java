package com.axon.demotask;

/**
 * Contains data of the post such as text. This bean does not provide user ID or blog post ID.
 */
public class BlogPostDataBean {
    /**
     * Content of the blog post.
     */
    private String text;

    public BlogPostDataBean() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
