package com.craftbeer.service;

import com.craftbeer.dao.UserDAO;
import com.craftbeer.model.Beer;
import com.craftbeer.model.User;
import com.craftbeer.modelview.BeerInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public User getUser(int id) {
        User user = userDAO.getUser(id);
        return user;
    }

    public boolean addBeer(String publicId, Beer beer) {
        User user = userDAO.getUserByPublicId(publicId);
        if (user != null) {
            boolean exist = false;
            for (Beer beer1 : user.getBeerList()) {
                if (beer.getId() == beer1.getId()) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                user.getBeerList().add(beer);
                userDAO.updateUser(user);
                return true;
            }
        }
        return false;
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public boolean newAdmin(String userName, String password) {
        return userDAO.newAdmin(userName, password);
    }

    public boolean newCustomer(String userName, String password) {
        return userDAO.newCustomer(userName, password);
    }

    public List<String> getAdminList() {
        List<User> adminList = userDAO.getAdminList();
        List<String> result = new ArrayList<>();
        for (User user : adminList) {
            result.add(user.getUsername());
        }
        return result;
    }

    public String getPublicId(String username, String password) {
        User user = userDAO.getUserByUsernameAndPassword(username, password);
        if (user != null) {
            return user.getPublicId();
        } else {
            return null;
        }
    }

    public List<Integer> getListBeer(String publicId) {
        User user = userDAO.getUserByPublicId(publicId);
        if (user != null) {
            List<Integer> beerList = new ArrayList<>();
            for (Beer beer: user.getBeerList()) {
                beerList.add(beer.getId());
            }
            return beerList;
        } else {
            return null;
        }
    }

}
