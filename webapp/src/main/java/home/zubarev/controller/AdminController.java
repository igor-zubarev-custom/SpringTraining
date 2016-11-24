package home.zubarev.controller;

import home.zubarev.service.OrderService;
import home.zubarev.web.dto.OrderStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/admin")
    public String admin(Model model){
        model.addAttribute("orders", orderService.getOrderList());
        return "admin";
    }

    @RequestMapping(value = "/admin/changeOrderStatus", method = RequestMethod.POST)
    public ResponseEntity changeOrderStatus(@RequestBody OrderStatusDto orderStatus){
        orderService.changeOrderStatus(orderStatus.getOrderId(), orderStatus.getStatusNumber());
        return new ResponseEntity(HttpStatus.OK);
    }
}
