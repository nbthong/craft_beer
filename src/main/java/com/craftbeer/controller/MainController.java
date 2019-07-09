package com.craftbeer.controller;

import com.craftbeer.modelview.BeerInformation;
import com.craftbeer.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BeerService beerService;
    @RequestMapping(value = "/")
    public ModelAndView start() {
        ModelAndView model = new ModelAndView("redirect:/login");
        return model;
    }
    @RequestMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView("login");
        return model;
    }

    @RequestMapping(value = "/getBeerList", method = RequestMethod.GET)
    public ResponseEntity<List<BeerInformation>> getBeerList(){
        List<BeerInformation> beerInformationList = beerService.getAvailableBeer();
        return new ResponseEntity<List<BeerInformation>>(beerInformationList,HttpStatus.OK);
    }

}
