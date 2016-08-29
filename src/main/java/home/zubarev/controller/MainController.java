package home.zubarev.controller;

import home.zubarev.dao.PhoneDao;
import home.zubarev.model.OrderItem;
import home.zubarev.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Igor Zubarev on 29.08.2016.
 */
@Controller
@SessionAttributes("order")
public class MainController {
    @Autowired
    PhoneDao phoneDao;

    @RequestMapping(value = "/")
    public ModelAndView home(ModelAndView model){
        //return "home";
        Phone phone = phoneDao.findAll().get(0);
        System.out.println(phone);
        model.addObject("phone", phone);
        model.setViewName("home");
        return model;
    }
    @RequestMapping(value = "/products")
    public ModelAndView productList (ModelAndView model){
        List<Phone> productList = phoneDao.findAll();
        model.addObject("productList", productList);
        model.setViewName("productList");
        return model;
    }
    @RequestMapping(value = "/product")
    public ModelAndView product (@RequestParam Long id){
        Phone product = phoneDao.get(id);
        ModelAndView model = new ModelAndView();
        model.addObject("product", product);
        model.setViewName("product");
        return model;
    }
    /*@RequestMapping(value = "/addToCart")
    public ModelAndView addToCart (@ModelAttribute OrderItem orderItem){
        Phone product = phoneDao.get(id);
        ModelAndView model = new ModelAndView();
        model.addObject("product", product);
        model.setViewName("product");
        return model;
    }*/
}
