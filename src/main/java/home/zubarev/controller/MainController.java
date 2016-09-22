package home.zubarev.controller;

import home.zubarev.dao.PhoneDao;
import home.zubarev.model.*;
import home.zubarev.model.dto.OrderItemIdDTO;
import home.zubarev.model.formdata.CartFormData;
import home.zubarev.model.formdata.CartInfo;
import home.zubarev.model.formdata.FormResponse;
import home.zubarev.model.formdata.ProductFormData;
import home.zubarev.model.validation.CartFormValidator;
import home.zubarev.service.IdGenerator;
import home.zubarev.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Zubarev on 29.08.2016.
 *
 * TODO: every page must go to a separate controller
 * The following controllers names are standard for ecommerce:
 * ProductListController
 * ProductDetailsController
 * CartController
 * OrderController
 * OrderConfirmationController
 *
 * Most of the time a customer works with a cart. Cart is stored in HTTP serssion.
 * During order placement a cart is transformed into an Order and persisted into the DB.
 *
 * Rename all the classes according to this rules please.
 */
@Controller
@SessionAttributes("order")
public class MainController {
    @Autowired
    PhoneDao phoneDao;
    @Autowired
    OrderService orderService;
    @Autowired
    CartFormValidator cartFormValidator;

    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";

    @RequestMapping(value = "/")
    // no need to return ModelAndView here, can return just string. It will be view name
    public ModelAndView home(ModelAndView model){
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

    @RequestMapping(value = "/cart")
    public ModelAndView cart (ModelAndView model){
        model.setViewName("cart");
        return model;
    }

    // TODO: pass productId inside URL to make links bookmarkable
    @RequestMapping(value = "/product/{id}")
    public ModelAndView product (@RequestParam Long id){
        Phone product = phoneDao.get(id);
        ModelAndView model = new ModelAndView();
        model.addObject("product", product);
        model.setViewName("product");
        return model;
    }


    @RequestMapping(value = "/cartInfo", method = RequestMethod.GET)
    public @ResponseBody
    CartInfo product (HttpServletRequest request){
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        CartInfo cartInfo = new CartInfo();
        if (order != null){
            cartInfo.setQuantity(orderService.getAllItems(order));
            cartInfo.setPrice(orderService.getOrderPrice(order));
            return cartInfo;
        }else {
            cartInfo.setQuantity(0L);
            cartInfo.setPrice(BigDecimal.valueOf(0));
            return cartInfo;
        }
    }

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    // TODO: move cart calculation to a service
    public @ResponseBody
    FormResponse addToCart(@Valid @RequestBody ProductFormData productFormData, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        FormResponse response = new FormResponse();
        if (!bindingResult.hasErrors()) {
            HttpSession session = httpServletRequest.getSession();
            Order order = (Order) session.getAttribute("order");
            OrderItem orderItem = new OrderItem();
            orderItem.setId(IdGenerator.generateId());
            orderItem.setPhone(phoneDao.get(productFormData.getId()));
            orderItem.setQuantity(productFormData.getQuantity());
            if (order != null) {
                orderService.addOrderItem(order, orderItem);
                session.setAttribute("order", order);
                response.setStatus(SUCCESS);
                List<String> messages = new ArrayList<>();
                messages.add("Product has been added to cart");
                response.setMessageList(messages);
                return response;
            } else {
                order = new Order();
                orderService.addOrderItem(order, orderItem);
                session.setAttribute("order", order);
                response.setStatus(SUCCESS);
                List<String> messages = new ArrayList<>();
                messages.add("Product has been added to cart");
                response.setMessageList(messages);
                return response;
            }
        }else {
            response.setStatus(ERROR);
            response.setMessageList(bindingResult.getAllErrors());
            return response;
        }

    }

    @RequestMapping(value = "/deleteFromCart", method = RequestMethod.POST)
    public @ResponseBody FormResponse deleteFromCrat (@Valid @RequestBody OrderItemIdDTO idDTO, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        FormResponse response = new FormResponse();
        List<String> messages = new ArrayList<>();
        if (!bindingResult.hasErrors()){
            HttpSession session = httpServletRequest.getSession();
            Order order = (Order) session.getAttribute("order");
            if (order != null){
                if(orderService.deleteOrderItem(order, idDTO.getId())){
                    response.setStatus(SUCCESS);
                    messages.add("Item has been deleted from cart");
                    response.setMessageList(messages);
                    return response;
                }else {
                    response.setStatus(ERROR);
                    messages.add("Item has not been deleted from cart");
                    response.setMessageList(messages);
                    return response;
                }
            }else {
                response.setStatus(ERROR);
                messages.add("Order not found");
                return response;
            }
        }else {
            response.setStatus(ERROR);
            response.setMessageList(bindingResult.getAllErrors());
            return response;
        }
    }

    @RequestMapping(value = "/updateCart", method = RequestMethod.POST)
    public ModelAndView updateCart(@ModelAttribute CartFormData cartFormData, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        if (bindingResult.hasErrors()){
            ModelAndView model = new ModelAndView();
            model.setViewName("cart");
            String errors = "Incorrect input";
            model.addObject("errorMessage", errors);
            return model;
        }

        cartFormValidator.validate(cartFormData, bindingResult);

        if (bindingResult.hasErrors()){
            ModelAndView model = new ModelAndView();
            model.setViewName("cart");
            String errors = "";
            for (ObjectError objectError :bindingResult.getAllErrors()) {
                errors += objectError.getDefaultMessage() + "; ";
            }
            // TODO: remove this. Use spring tags to render binding result errors
            model.addObject("errorMessage", errors);
            return model;
        }
        Order order = (Order)httpServletRequest.getSession().getAttribute("order");
        orderService.updateOrder(order, cartFormData);
        return new ModelAndView("cart");
    }
}
