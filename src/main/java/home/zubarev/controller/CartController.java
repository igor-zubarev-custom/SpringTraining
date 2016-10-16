package home.zubarev.controller;

import home.zubarev.model.Cart;
import home.zubarev.model.CartItem;
import home.zubarev.service.CartService;
import home.zubarev.web.dto.CartItemIdDTO;
import home.zubarev.web.formdata.CartFormData;
import home.zubarev.web.formdata.CartInfo;
import home.zubarev.web.formdata.FormResponse;
import home.zubarev.web.formdata.ProductFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    CartService cartService;

    @RequestMapping(value = "/cart")
    public ModelAndView cart (ModelAndView model){
        model.setViewName("cart");
        return model;
    }

    @RequestMapping(value = "/cartInfo", method = RequestMethod.GET)
    public @ResponseBody
    CartInfo product (HttpServletRequest request){
        Cart cart = cartService.getCart(request);
        CartInfo cartInfo = new CartInfo();
        if (cart != null){
            cartInfo.setQuantity(cartService.getAllItems(cart));
            cartInfo.setPrice(cartService.getOrderPrice(cart));
            return cartInfo;
        }else {
            cartInfo.setQuantity(0L);
            cartInfo.setPrice(BigDecimal.valueOf(0));
            return cartInfo;
        }
    }

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public @ResponseBody
    FormResponse addToCart(@Valid @RequestBody ProductFormData productFormData, BindingResult bindingResult, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        FormResponse response = new FormResponse();
        if (!bindingResult.hasErrors()) {
            HttpSession session = httpServletRequest.getSession();
            Cart cart = cartService.getCart(httpServletRequest);
            CartItem cartItem = cartService.createCartItem(productFormData);
            if (cart == null) {
                cart = new Cart();
            }
            cartService.addOrderItem(cart, cartItem);
            session.setAttribute("cart", cart);
            List<String> messages = new ArrayList<>();
            messages.add("Product has been added to cart");
            response.setMessageList(messages);
            return response;

        }else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setMessageList(bindingResult.getAllErrors());
            return response;
        }

    }
    /*TODO:1*/
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public @ResponseBody FormResponse handleHttpMessageNotReadableException (HttpMessageNotReadableException exception){
        FormResponse formResponse = new FormResponse();
        List<String> messages = new ArrayList<>();
        messages.add("Quantity must be a number");
        formResponse.setMessageList(messages);
        return formResponse;
    }

    @RequestMapping(value = "/deleteFromCart", method = RequestMethod.POST)
    public @ResponseBody FormResponse deleteFromCart (@Valid @RequestBody CartItemIdDTO idDTO, BindingResult bindingResult, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        FormResponse response = new FormResponse();
        List<String> messages = new ArrayList<>();
        if (!bindingResult.hasErrors()){
            Cart cart = cartService.getCart(httpServletRequest);
            if (cart != null){
                if(cartService.deleteOrderItem(cart, idDTO.getId())){
                    messages.add("Item has been deleted from cart");
                    response.setMessageList(messages);
                    return response;
                }else {
                    httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    messages.add("Item has not been deleted from cart");
                    response.setMessageList(messages);
                    return response;
                }
            }else {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                messages.add("Cart not found");
                return response;
            }
        }else {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            response.setMessageList(bindingResult.getAllErrors());
            return response;
        }
    }

    @RequestMapping(value = "/updateCart", method = RequestMethod.POST)
    public ModelAndView updateCart(@Valid @ModelAttribute CartFormData cartFormData, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        if (bindingResult.hasErrors()){
            ModelAndView model = new ModelAndView();
            model.setViewName("cart");
            return model;
        }
        Cart cart = (Cart)httpServletRequest.getSession().getAttribute("cart");
        cartService.updateOrder(cart, cartFormData);
        return new ModelAndView("cart");
    }
}
