package home.zubarev.controller;

import home.zubarev.service.CartService;
import home.zubarev.service.OrderService;
import home.zubarev.web.formdata.AddressFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
//@SessionAttributes("order")
public class OrderController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/shop/order")
    public String order(Model model){
        model.addAttribute("addressFormData", new AddressFormData());
        model.addAttribute("cartService", cartService);
        return "order";
    }
    @RequestMapping(value = "/shop/order", method = RequestMethod.POST)
    public String order(@Valid @ModelAttribute("addressFormData") AddressFormData addressFormData, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("cartService", cartService);
            return "order";
        }
        cartService.setDeliveryInfo(
                addressFormData.getFirstName(),
                addressFormData.getLastName(),
                addressFormData.getAddress(),
                addressFormData.getPhone(),
                addressFormData.getComment());

        orderService.createOrder();
        return "redirect:/shop/orderConfirmation";
    }
}
