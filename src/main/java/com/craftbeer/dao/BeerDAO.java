package com.craftbeer.dao;

import com.craftbeer.common.BeerStatus;
import com.craftbeer.model.Beer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BeerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public Beer getBeer(int id){
        Beer beer = (Beer) getCurrentSession().get(Beer.class,id);
        return beer;
    }

    public List<Beer> getAvailableBeer(){
        Criteria cr = getCurrentSession().createCriteria(Beer.class);
        cr.add(Restrictions.eq("status",BeerStatus.AVAILABLE.getValue()));
        return cr.list();
    }

    public List<Beer> getBeerList(){
        Criteria cr = getCurrentSession().createCriteria(Beer.class);
        return cr.list();
    }

    public void addBeer(Beer beer){
        getCurrentSession().save(beer);
    }

    public void updateBeer(Beer beer) {
        getCurrentSession().update(beer);
    }

    public void deleteBeer(Beer beer){
        getCurrentSession().delete(beer);
    }
}
