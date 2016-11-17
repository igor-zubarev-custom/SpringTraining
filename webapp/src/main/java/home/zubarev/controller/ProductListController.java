package home.zubarev.controller;

import home.zubarev.dao.PhoneDao;
import home.zubarev.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
public class ProductListController {
    @Autowired
    private PhoneDao phoneDao;

    @RequestMapping(value = "/products")
    public String productList (Model model){
        List<Phone> productList = productList = phoneDao.getPhones();
        model.addAttribute("productList", productList);
        return "productList";
    }
}
