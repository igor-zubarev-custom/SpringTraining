package home.zubarev.controller;

import home.zubarev.dao.PhoneDao;
import home.zubarev.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductDetailsController {
    @Autowired
    private PhoneDao phoneDao;

    @RequestMapping(value = "/product/{id}")
    public String product (@PathVariable("id") Long id, Model model){
        Phone product = phoneDao.getPhone(id);
        model.addAttribute("product", product);
        return "product";
    }
}
