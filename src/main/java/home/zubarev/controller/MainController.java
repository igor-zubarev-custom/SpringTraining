package home.zubarev.controller;

import home.zubarev.dao.PhoneDao;
import home.zubarev.model.Order;
import home.zubarev.model.OrderItem;
import home.zubarev.model.Phone;
import home.zubarev.model.ProductFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
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
    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public ModelAndView addToCart(@RequestBody ProductFormData productFormData, HttpServletRequest httpServletRequest, ModelAndView model){
        HttpSession session = httpServletRequest.getSession();
        Order order = (Order) session.getAttribute("order");
        OrderItem orderItem = new OrderItem();
        orderItem.setPhone(phoneDao.get(productFormData.getId()));
        orderItem.setQuantity(productFormData.getQuantity());
        if (order != null){
            order.getOrderItems().add(orderItem);
            session.setAttribute("order", order);
            model.setViewName("productList");
            return model;
        }else {
            order = new Order();
            List<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(orderItem);
            order.setOrderItems(orderItems);
            session.setAttribute("order", order);
            model.setViewName("productList");
            return model;
        }

    }
}
