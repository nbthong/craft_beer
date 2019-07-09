package com.craftbeer.controller;

import com.craftbeer.model.Beer;
import com.craftbeer.modelview.Passport;
import com.craftbeer.service.BeerService;
import com.craftbeer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private BeerService beerService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestParam(value = "username") String username,
                                 @RequestParam(value = "password") String password) {
        boolean result = userService.newCustomer(username, password);
        if (result) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam(value = "username") String username,
                                @RequestParam(value = "password") String password) {
        String publicId = userService.getPublicId(username, password);
        if (publicId != null) {
            return new ResponseEntity(publicId, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/addBeer", method = RequestMethod.POST)
    public ResponseEntity addBeer(@RequestParam(value = "publicId") String publicId,
                                  @RequestParam(value = "beerId") int beerId) {
        Beer beer = beerService.getBeer(beerId);
        if (beer != null) {
            boolean result = userService.addBeer(publicId, beer);
            if (result) {
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/getBeerList", method = RequestMethod.POST)
    public ResponseEntity<Passport> addBeer(@RequestParam(value = "publicId") String publicId) {
        List<Integer> beerList = userService.getListBeer(publicId);
        if (beerList != null){
            Passport passport = beerService.getPassport(beerList);
            return new ResponseEntity(passport, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

    }
}
