package com.craftbeer.service;

import ch.qos.logback.core.pattern.ConverterUtil;
import com.craftbeer.commonutils.ConverterUtils;
import com.craftbeer.dao.CategoryDAO;
import com.craftbeer.model.Category;
import com.craftbeer.modelview.CategoryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Convert;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public Category getCategory(int id){
        Category category = categoryDAO.getCategory(id);
        return category;
    }

    public List<CategoryView> getCategoryList() {
        List<Category> categoryList = categoryDAO.getCategoryList();
        List<CategoryView> result = new ArrayList<>();
        for (Category category: categoryList) {
            CategoryView categoryView = ConverterUtils.convertToCategoryView(category);
            result.add(categoryView);
        }
        return result;
    }

    public void addCategory(String name){
        Category category = new Category();
        category.setName(name);
        categoryDAO.addCategory(category);
    }

    public boolean deleteCategory(int id){
        Category category = categoryDAO.getCategory(id);
        if (category!= null && category.getBeerList().size() == 0){
            categoryDAO.deleteCategory(category);
        } else {
            return false;
        }
        return true;
    }

    public boolean updateCategory(int id, String name){
        Category category = categoryDAO.getCategory(id);
        if (category != null){
            category.setName(name);
            categoryDAO.updateCategory(category);
        } else {
            return false;
        }
        return true;
    }
}
