package home.zubarev.controller;

import home.zubarev.service.CartService;
import home.zubarev.web.dto.CartItemDTO;
import home.zubarev.web.dto.CartItemIdDTO;
import home.zubarev.web.formdata.CartFormData;
import home.zubarev.web.formdata.CartInfo;
import home.zubarev.web.formdata.FormResponse;
import home.zubarev.web.formdata.ProductFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart")
    public String cart (){
        return "cart";
    }

    @RequestMapping(value = "/cartInfo", method = RequestMethod.GET)
    public @ResponseBody CartInfo product (){
        CartInfo cartInfo = new CartInfo();
        cartInfo.setQuantity(cartService.getTotalQuantity());
        cartInfo.setPrice(cartService.getCartPrice());
        return cartInfo;
    }

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public @ResponseBody
    FormResponse addToCart(@Valid @RequestBody ProductFormData productFormData, BindingResult bindingResult, HttpServletResponse httpServletResponse){
        FormResponse response = new FormResponse();
        List<String> messages = new ArrayList<>();
        if (!bindingResult.hasErrors()) {
            try {
                cartService.addItemToCart(productFormData.getId(), productFormData.getQuantity());
            } catch (DataAccessException e) {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                messages.add("Product cant be added to cart");
                response.setMessageList(messages);
                return response;
            }
            messages.add("Product has been added to cart");
            response.setMessageList(messages);
            return response;

        }else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setMessageList(convertBindingResultToMessageList(bindingResult));
            return response;
        }

    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public @ResponseBody FormResponse handleHttpMessageNotReadableException (HttpMessageNotReadableException exception, HttpServletResponse httpServletResponse){
        FormResponse formResponse = new FormResponse();
        List<String> messages = new ArrayList<>();
        messages.add("Quantity must be a number");
        formResponse.setMessageList(messages);
        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return formResponse;
    }

    @RequestMapping(value = "/deleteFromCart", method = RequestMethod.POST)
    public @ResponseBody FormResponse deleteFromCart (@Valid @RequestBody CartItemIdDTO idDTO, BindingResult bindingResult, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        FormResponse response = new FormResponse();
        List<String> messages = new ArrayList<>();
        if (!bindingResult.hasErrors()){
            if(cartService.deleteCartItem(idDTO.getId())){
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
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            response.setMessageList(convertBindingResultToMessageList(bindingResult));
            return response;
        }
    }

    @RequestMapping(value = "/updateCart", method = RequestMethod.POST)
    public String updateCart (@Valid @ModelAttribute CartFormData cartFormData, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "cart";
        }
        cartService.updateCart(convertCartFormDataToMap(cartFormData));
        return "cart";
    }

    private List<String> convertBindingResultToMessageList (BindingResult bindingResult){
        List<String> messages = new ArrayList<>();
        for (ObjectError objectError : bindingResult.getAllErrors()) {
            messages.add(objectError.getDefaultMessage());
        }
        return messages;
    }

    private Map<Long, Long> convertCartFormDataToMap (CartFormData cartFormData){
        Map<Long, Long> cartModifier = new ConcurrentHashMap<>();
        for (CartItemDTO cartItemDto : cartFormData.getCartItemDTOs()) {
            cartModifier.put(cartItemDto.getId(), cartItemDto.getQuantity());
        }
        return cartModifier;
    }
}
