package home.zubarev.controller;

import home.zubarev.dao.PhoneDao;
import home.zubarev.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.sql.SQLException;

@Controller
public class ProductDetailsController {
    @Autowired
    @Resource(name = "phoneJdbcTemplateDaoImpl")
    private PhoneDao phoneDao;

    @RequestMapping(value = "/product/{id}")
    public ModelAndView product (@PathVariable("id") Long id){
        Phone product = null;
        ModelAndView model = new ModelAndView();
        try {
            product = phoneDao.getPhone(id);
        } catch (DataAccessException e) {
            model.setViewName("error");
        }
        model.addObject("product", product);
        model.setViewName("product");
        return model;
    }
}
