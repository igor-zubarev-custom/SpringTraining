package home.zubarev.controller;

import home.zubarev.service.CartService;
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
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orderConfirmation")
    public String orderConfirmation(Model model){
        model.addAttribute("order", orderService.getOrder());
        return "orderConfirmation";
    }

    @RequestMapping(value = "/confirmOrder")
    public String confirmOrder(Model model){
        Long orderId = orderService.saveOrder();
        orderService.deleteOrder();
        cartService.clearCart();
        model.addAttribute("orderId", orderId);
        return "redirect:/success";
    }

    @RequestMapping(value = "/success")
    public String success(){
        return "success";
    }
}
