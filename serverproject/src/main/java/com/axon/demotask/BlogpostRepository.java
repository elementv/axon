package com.axon.demotask;

import com.axon.demotask.database.model.Blogpost;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

public class BlogpostRepository {
    @Autowired
    private SessionFactory sessionFactory;

    private List<ExistingBlogPostBean> allBlogposts = new ArrayList<>();
    private int blogpostIDCounter = 0;

    public List<ExistingBlogPostBean> getAllBlogposts() {
        Session session = sessionFactory.openSession();
        return (List<ExistingBlogPostBean>) session.createQuery("FROM Blogpost").list();
    }

    public List<ExistingBlogPostBean> getAllOwnBlogposts(String username) {
        Session session = sessionFactory.openSession();
        //return allBlogposts.stream().filter(blogpost -> blogpost.getAuthorID().equals(username)).collect(Collectors.toList());
        Query query = session.createQuery("FROM Blogpost WHERE AUTHOR_ID = :username");
        query.setParameter("username", username);
        return (List<ExistingBlogPostBean>) ((org.hibernate.query.Query) query).list();
    }

    public ExistingBlogPostBean getBlogpostByID(String blogpostid) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Blogpost WHERE BLOGPOSTID = :blogpostid");
        query.setParameter("blogpostid", blogpostid);
        List<Blogpost> blogposts = (List<Blogpost>) ((org.hibernate.query.Query) query).list();
        System.out.println("Blog Post size: " + blogposts.size());
        if (blogposts.size() == 0) {
            return null;
        } else if (blogposts.size() == 1) {
            return Blogpost.toExistingBlogPostBean(blogposts.get(0));
        } else {
            throw new RuntimeException("More than 1 blog post with the same id found.");
        }
    }

    public ExistingBlogPostBean createOwnBlogpost(BlogPostDataBean newBlogpostData, String username) {
        System.out.println("Method updateOwnBlogpost executed.");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //Add new Employee object
        Blogpost blogpost = new Blogpost();
        blogpost.setText(newBlogpostData.getText());
        blogpost.setAuthorid(username);
        //Save the employee in database
        session.save(blogpost);
        //Commit the transaction
        session.getTransaction().commit();
        return Blogpost.toExistingBlogPostBean(blogpost);
    }

    public void updateOwnBlogpost(BlogPostDataBean newBlogpostData, String blogpostid) {
        System.out.println("Method updateOwnBlogpost executed.");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Blogpost set text = :text WHERE BLOGPOSTID = :blogpostid");
        query.setParameter("text", newBlogpostData.getText());
        query.setParameter("blogpostid", blogpostid);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void deleteOwnBlogpost(String blogpostid) {
        System.out.println("Method deleteOwnBlogpost executed.");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE Blogpost WHERE BLOGPOSTID = :blogpostid");
        query.setParameter("blogpostid", blogpostid);
        query.executeUpdate();
        session.getTransaction().commit();
    }
}
