package com.craftbeer.controller;

import com.craftbeer.model.Beer;
import com.craftbeer.model.Category;
import com.craftbeer.modelview.BeerDetail;
import com.craftbeer.modelview.BeerView;
import com.craftbeer.modelview.CategoryView;
import com.craftbeer.service.BeerService;
import com.craftbeer.service.CategoryService;
import com.craftbeer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private BeerService beerService;

    @RequestMapping(value = "/beer", method = RequestMethod.GET)
    public ModelAndView beer() {
        ModelAndView model = new ModelAndView("admin/beer");
        List<CategoryView> categoryList = categoryService.getCategoryList();
        model.addObject("categoryList", categoryList);
        List<BeerView> beerList = beerService.getBeerList();
        model.addObject("beerList", beerList);
        return model;
    }

    @RequestMapping(value = "/beerDetail/{id}", method = RequestMethod.GET)
    public ModelAndView beerDetail(@PathVariable("id") int id) {
        ModelAndView model = new ModelAndView("admin/beer_detail");
        BeerDetail beerDetail = beerService.getBeerDetail(id);
        model.addObject("beer", beerDetail);
        List<CategoryView> categoryList = categoryService.getCategoryList();
        model.addObject("categoryList", categoryList);
        return model;
    }

    @RequestMapping(value = "/archiveBeer/{id}", method = RequestMethod.GET)
    public ModelAndView archiveBeer(@PathVariable("id") int id) {
        beerService.archiveBeer(id);
        return new ModelAndView("redirect:/admin/beerDetail/" + id);
    }

    @RequestMapping(value = "/activeBeer/{id}", method = RequestMethod.GET)
    public ModelAndView activeBeer(@PathVariable("id") int id) {
        beerService.activeBeer(id);
        return new ModelAndView("redirect:/admin/beerDetail/" + id);
    }

    @RequestMapping(value = "/updateBeer", method = RequestMethod.POST)
    public ModelAndView updateBeer(@RequestParam(value = "id") int id,
                                   @RequestParam(value = "category") int categoryId,
                                   @RequestParam(value = "name") String name,
                                   @RequestParam(value = "manufacturer") String manufacturer,
                                   @RequestParam(value = "country") String country,
                                   @RequestParam(value = "price") double price,
                                   @RequestParam(value = "description") String description) {
        ModelAndView model = new ModelAndView("admin/beer_detail");
        Category category = categoryService.getCategory(categoryId);
        if (category != null) {
            beerService.updateBeer(id, category, name, manufacturer, country, price, description);
            model.addObject("msg", "Successfully");
        } else {
            model.addObject("msg", "Invalid");
        }
        BeerDetail beerDetail = beerService.getBeerDetail(id);
        model.addObject("beer", beerDetail);
        List<CategoryView> categoryList = categoryService.getCategoryList();
        model.addObject("categoryList", categoryList);
        return model;
    }

    @RequestMapping(value = "/newBeer", method = RequestMethod.POST)
    public ModelAndView newBeer(@RequestParam(value = "category") int categoryId,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "manufacturer") String manufacturer,
                                @RequestParam(value = "country") String country,
                                @RequestParam(value = "price") double price,
                                @RequestParam(value = "description") String description) {
        ModelAndView model = new ModelAndView("admin/beer");
        Category category = categoryService.getCategory(categoryId);
        if (category != null) {
            beerService.newBeer(category, name, manufacturer, country, price, description);
            model.addObject("msg", "Successfully");
        } else {
            model.addObject("msg", "Invalid");
        }
        List<CategoryView> categoryList = categoryService.getCategoryList();
        model.addObject("categoryList", categoryList);
        List<BeerView> beerList = beerService.getBeerList();
        model.addObject("beerList", beerList);
        return model;
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ModelAndView category() {
        ModelAndView model = new ModelAndView("admin/category");
        List<CategoryView> categoryList = categoryService.getCategoryList();
        model.addObject("categoryList", categoryList);
        return model;
    }

    @RequestMapping(value = "/newCategory", method = RequestMethod.POST)
    public ModelAndView newCategory(@RequestParam(value = "name") String name) {
        categoryService.addCategory(name);
        ModelAndView model = new ModelAndView("admin/category");
        model.addObject("msg", "Successfully");
        List<CategoryView> categoryList = categoryService.getCategoryList();
        model.addObject("categoryList", categoryList);
        return model;
    }

    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
    public ModelAndView newCategory(@RequestParam(value = "id") int id,
                                    @RequestParam(value = "name") String name) {
        boolean result = categoryService.updateCategory(id, name);
        ModelAndView model = new ModelAndView("admin/category");
        if (result) {
            model.addObject("msg", "Successfully");
        } else {
            model.addObject("msg", "Invalid");
        }
        List<CategoryView> categoryList = categoryService.getCategoryList();
        model.addObject("categoryList", categoryList);
        return model;
    }

    @RequestMapping(value = "/deleteCategory/{id}", method = RequestMethod.GET)
    public ModelAndView deleteCategory(@PathVariable("id") int id) {
        boolean result = categoryService.deleteCategory(id);
        ModelAndView model = new ModelAndView("admin/category");
        if (result) {
            model.addObject("msg", "Successfully");
        } else {
            model.addObject("msg", "Invalid");
        }
        List<CategoryView> categoryList = categoryService.getCategoryList();
        model.addObject("categoryList", categoryList);
        return model;
    }

    @RequestMapping(value = "/administrator", method = RequestMethod.GET)
    public ModelAndView administrator() {
        ModelAndView model = new ModelAndView("admin/administrator");
        List<String> adminList = userService.getAdminList();
        model.addObject("adminList", adminList);
        return model;
    }

    @RequestMapping(value = "/newAdmin", method = RequestMethod.POST)
    public ModelAndView newAdmin(@RequestParam(value = "username") String username,
                                 @RequestParam(value = "password") String password) {
        ModelAndView model = new ModelAndView("admin/administrator");
        boolean result = userService.newAdmin(username, password);
        if (result) {
            model.addObject("msg", "Successfully");
        } else {
            model.addObject("msg", "Invalid");
        }
        List<String> adminList = userService.getAdminList();
        model.addObject("adminList", adminList);
        return model;
    }


}
