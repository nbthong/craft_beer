package com.craftbeer.dao;

import com.craftbeer.common.TokenUtils;
import com.craftbeer.common.UserRole;
import com.craftbeer.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public User getUser(int id){
        User user = (User) getCurrentSession().get(User.class,id);
        return user;
    }

    public User getUserByUsername(String username){
        Criteria cr = getCurrentSession().createCriteria(User.class);
        cr.add(Restrictions.eq("username",username));
        if (cr.list().size()>0){
            return (User) cr.list().get(0);
        }
        return null;
    }

    public User getUserByUsernameAndPassword(String username, String password){
        Criteria cr = getCurrentSession().createCriteria(User.class);
        cr.add(Restrictions.eq("username",username));
        if (cr.list().size()>0){
            User user = (User) cr.list().get(0);
            boolean result = new BCryptPasswordEncoder().matches(password,user.getPassword());
            if (result){
                return user;
            }
        }
        return null;
    }

    public User getUserByPublicId(String publicId){
        Criteria cr = getCurrentSession().createCriteria(User.class);
        cr.add(Restrictions.eq("publicId",publicId));
        if (cr.list().size()>0){
            return (User) cr.list().get(0);
        }
        return null;
    }

    public List<User> getAdminList(){
        Criteria cr = getCurrentSession().createCriteria(User.class);
        cr.add(Restrictions.eq("role",UserRole.ADMIN.getValue()));
        return cr.list();
    }

    public boolean newAdmin(String username, String password){
        Criteria cr = getCurrentSession().createCriteria(User.class);
        cr.add(Restrictions.eq("username",username));
        if (cr.list().size()>0){
           return false;
        } else {
            User user = new User();
            user.setUsername(username);
            String passwordEncoded = new BCryptPasswordEncoder().encode(password);
            user.setPassword(passwordEncoded);
            String publicId = TokenUtils.generatePublicId(username);
            user.setPublicId(publicId);
            user.setRole(UserRole.ADMIN.getValue());
            getCurrentSession().save(user);
        }
        return true;
    }

    public boolean newCustomer(String username, String password){
        Criteria cr = getCurrentSession().createCriteria(User.class);
        cr.add(Restrictions.eq("username",username));
        if (cr.list().size()>0){
            return false;
        } else {
            User user = new User();
            user.setUsername(username);
            String passwordEncoded = new BCryptPasswordEncoder().encode(password);
            user.setPassword(passwordEncoded);
            String publicId = TokenUtils.generatePublicId(username);
            user.setPublicId(publicId);
            user.setRole(UserRole.CUSTOMER.getValue());
            getCurrentSession().save(user);
        }
        return true;
    }

    public void addUser(User user){
        getCurrentSession().save(user);
    }

    public void updateUser(User user) {
        getCurrentSession().update(user);
    }

    public void deleteUser(User user){
        getCurrentSession().delete(user);
    }
}
