package com.craftbeer.dao;

import com.craftbeer.model.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public Category getCategory(int id){
        Category category = (Category) getCurrentSession().get(Category.class,id);
        return category;
    }

    public List<Category> getCategoryList(){
        Criteria cr = getCurrentSession().createCriteria(Category.class);
        return cr.list();
    }
    public void addCategory(Category category){
        getCurrentSession().save(category);
    }

    public void updateCategory(Category category) {
        getCurrentSession().update(category);
    }

    public void deleteCategory(Category category){
        getCurrentSession().delete(category);
    }
}
