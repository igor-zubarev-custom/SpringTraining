package home.zubarev.controller;

import home.zubarev.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("orderId")
public class OrderConfirmationController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/shop/orderConfirmation")
    public String orderConfirmation(Model model){
        model.addAttribute("order", orderService.getOrder());
        return "orderConfirmation";
    }

    @RequestMapping(value = "/shop/confirmOrder")
    public String confirmOrder(Model model){
        Long orderId = orderService.placeOrder();
        model.addAttribute("orderId", orderId);
        return "redirect:/shop/success";
    }

    @RequestMapping(value = "/shop/success")
    public String success(){
        return "success";
    }
}
