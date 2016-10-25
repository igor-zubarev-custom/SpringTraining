package home.zubarev.controller;

import home.zubarev.dao.PhoneDao;
import home.zubarev.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Controller
public class ProductListController {
    @Autowired
    @Resource(name = "phoneJdbcTemplateDaoImpl")
    private PhoneDao phoneDao;

    @RequestMapping(value = "/products")
    public ModelAndView productList (ModelAndView model){
        List<Phone> productList = null;
        try {
            productList = phoneDao.getPhones();
        } catch (SQLException e) {
            model.setViewName("error");
            return model;
        }
        model.addObject("productList", productList);
        model.setViewName("productList");
        return model;
    }
}
