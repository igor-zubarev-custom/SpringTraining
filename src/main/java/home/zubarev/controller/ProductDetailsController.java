package home.zubarev.controller;

import home.zubarev.dao.PhoneDao;
import home.zubarev.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductDetailsController {
    @Autowired
    PhoneDao phoneDao;

    @RequestMapping(value = "/product/{id}")
    public ModelAndView product (@PathVariable("id") Long id){
        Phone product = phoneDao.get(id);
        ModelAndView model = new ModelAndView();
        model.addObject("product", product);
        model.setViewName("product");
        return model;
    }
}
