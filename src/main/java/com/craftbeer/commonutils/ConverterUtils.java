package com.craftbeer.commonutils;

import com.craftbeer.model.Beer;
import com.craftbeer.model.Category;
import com.craftbeer.modelview.BeerDetail;
import com.craftbeer.modelview.BeerInformation;
import com.craftbeer.modelview.BeerView;
import com.craftbeer.modelview.CategoryView;

public class ConverterUtils {
    public static BeerInformation convertToBeerInformation(Beer beer){
        BeerInformation beerInformation = new BeerInformation();
        beerInformation.setName(beer.getName());
        beerInformation.setCategory(beer.getCategory().getName());
        beerInformation.setCountry(beer.getCountry());
        beerInformation.setDescription(beer.getDescription());
        beerInformation.setManufacturer(beer.getManufacturer());
        beerInformation.setPrice(beer.getPrice());
        return beerInformation;
    }

    public static BeerView convertToBeerView(Beer beer){
        BeerView beerView = new BeerView();
        beerView.setId(beer.getId());
        beerView.setName(beer.getName());
        beerView.setStatus(beer.getStatus());
        return beerView;
    }

    public static BeerDetail convertToBeerDetail(Beer beer){
        BeerDetail beerDetail = new BeerDetail();
        beerDetail.setId(beer.getId());
        beerDetail.setName(beer.getName());
        beerDetail.setManufacturer(beer.getManufacturer());
        beerDetail.setCountry(beer.getCountry());
        beerDetail.setDescription(beer.getDescription());
        beerDetail.setPrice(beer.getPrice());
        beerDetail.setStatus(beer.getStatus());
        beerDetail.setCategoryId(beer.getCategory().getId());
        return beerDetail;
    }

    public static CategoryView convertToCategoryView(Category category){
        CategoryView categoryView = new CategoryView();
        categoryView.setId(category.getId());
        categoryView.setName(category.getName());
        categoryView.setAmountBeer(category.getBeerList().size());
        return categoryView;
    }
}
