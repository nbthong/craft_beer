package com.craftbeer.service;

import com.craftbeer.common.BeerStatus;
import com.craftbeer.commonutils.ConverterUtils;
import com.craftbeer.dao.BeerDAO;
import com.craftbeer.model.Beer;
import com.craftbeer.model.Category;
import com.craftbeer.modelview.BeerDetail;
import com.craftbeer.modelview.BeerInformation;
import com.craftbeer.modelview.BeerView;
import com.craftbeer.modelview.Passport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BeerService {

    @Autowired
    private BeerDAO beerDAO;

    public Beer getBeer(int id){
        return beerDAO.getBeer(id);
    }

    public BeerDetail getBeerDetail(int id){
        Beer beer = beerDAO.getBeer(id);
        BeerDetail beerDetail = ConverterUtils.convertToBeerDetail(beer);
        return beerDetail;
    }

    public List<BeerView> getBeerList(){
        List<Beer> beerList = beerDAO.getBeerList();
        List<BeerView> result = new ArrayList<>();
        for (Beer beer: beerList) {
            BeerView beerView = ConverterUtils.convertToBeerView(beer);
            result.add(beerView);
        }
        return result;
    }

    public List<BeerInformation> getAvailableBeer() {
        List<Beer> beerList = beerDAO.getAvailableBeer();
        List<BeerInformation> beerInformationList = new ArrayList<>();
        for (Beer beer: beerList) {
            beerInformationList.add(ConverterUtils.convertToBeerInformation(beer));
        }
        return beerInformationList;
    }

    public void newBeer(Category category, String name, String manufacturer,
                        String country,double price,String description){
        Beer newBeer = new Beer();
        newBeer.setCategory(category);
        newBeer.setName(name);
        newBeer.setManufacturer(manufacturer);
        newBeer.setCountry(country);
        newBeer.setPrice(price);
        newBeer.setDescription(description);
        newBeer.setStatus(BeerStatus.AVAILABLE.getValue());
        beerDAO.addBeer(newBeer);
    }

    public void updateBeer(int id, Category category, String name, String manufacturer,
                        String country,double price,String description){
        Beer beer = beerDAO.getBeer(id);
        beer.setCategory(category);
        beer.setName(name);
        beer.setManufacturer(manufacturer);
        beer.setCountry(country);
        beer.setPrice(price);
        beer.setDescription(description);
        beerDAO.updateBeer(beer);
    }

    public void archiveBeer(int id){
        Beer beer = beerDAO.getBeer(id);
        if (beer!=null){
            beer.setStatus(BeerStatus.ARCHIVE.getValue());
            beerDAO.updateBeer(beer);
        }
    }

    public void activeBeer(int id){
        Beer beer = beerDAO.getBeer(id);
        if (beer!=null){
            beer.setStatus(BeerStatus.AVAILABLE.getValue());
            beerDAO.updateBeer(beer);
        }
    }

    public Passport getPassport(List<Integer> beerList){
        Passport passport = new Passport();
        List<BeerInformation> triedList = new ArrayList<>();
        List<BeerInformation> availableList = new ArrayList<>();
        List<Beer> allBeer = beerDAO.getBeerList();
        for (Beer beer: allBeer) {
            boolean tried = false;
            for (Integer beerId: beerList) {
                if (beerId == beer.getId()){
                    tried = true;
                    break;
                }
            }
            BeerInformation beerInformation = ConverterUtils.convertToBeerInformation(beer);
            if (tried){
                triedList.add(beerInformation);
            } else if (beer.getStatus().equalsIgnoreCase(BeerStatus.AVAILABLE.getValue())){
                availableList.add(beerInformation);
            }
        }
        passport.setTriedBeerList(triedList);
        passport.setAvailableBeerList(availableList);
        return passport;
    }
}
