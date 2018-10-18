package com.axon.demotask.database.model;

import com.axon.demotask.ExistingBlogPostBean;

import javax.persistence.*;

@Entity
@Table(name = "blogposts", uniqueConstraints = {
        @UniqueConstraint(columnNames = "BLOGPOSTID")})
public class Blogpost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "BLOGPOSTID", unique = true, nullable = false)
    private Integer blogpostid;

    @Column(name = "TEXT", unique = false, nullable = false, length = 300)
    private String text;

    @Column(name = "AUTHOR_ID", unique = false, nullable = false, length = 300)
    private String authorid;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getBlogpostid() {
        return blogpostid;
    }

    public void setBlogpostid(int blogpostid) {
        this.blogpostid = blogpostid;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    /**
     * Converts an object of type Blogpost to type ExistingBlogPostBean.
     * @return
     */
    public static ExistingBlogPostBean toExistingBlogPostBean(Blogpost blogpost){
        ExistingBlogPostBean existingBlogPostBean = new ExistingBlogPostBean();
        existingBlogPostBean.setText(blogpost.getText());
        existingBlogPostBean.setPostID(String.valueOf(blogpost.getBlogpostid()));
        existingBlogPostBean.setAuthorID(blogpost.getAuthorid());
        return existingBlogPostBean;
    }
}
