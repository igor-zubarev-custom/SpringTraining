package home.zubarev.controller;

import home.zubarev.model.Cart;
import home.zubarev.model.CartItem;
import home.zubarev.service.CartService;
import home.zubarev.web.dto.CartItemDTO;
import home.zubarev.web.dto.CartItemIdDTO;
import home.zubarev.web.formdata.CartFormData;
import home.zubarev.web.formdata.CartInfo;
import home.zubarev.web.formdata.FormResponse;
import home.zubarev.web.formdata.ProductFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/shop/cart")
    public String cart (Model model){
        model.addAttribute("cartFormData", populateCartFormData());
        model.addAttribute("cart", cartService.getCart());
        return "cart";
    }

    private CartFormData populateCartFormData(){
        CartFormData cartFormData = new CartFormData();
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();
        Cart cart = cartService.getCart();
        for (CartItem cartItem : cart.getCartItems()) {
            cartItemDTOs.add(new CartItemDTO(cartItem.getId(), cartItem.getQuantity()));
        }
        cartFormData.setCartItemDTOs(cartItemDTOs);
        return cartFormData;
    }

    @RequestMapping(value = "/cartInfo", method = RequestMethod.GET)
    public @ResponseBody CartInfo product (){
        CartInfo cartInfo = new CartInfo();
        cartInfo.setQuantity(cartService.getTotalQuantity());
        cartInfo.setPrice(cartService.getCartPrice());
        return cartInfo;
    }

    @RequestMapping(value = "/shop/addToCart", method = RequestMethod.POST)
    public ResponseEntity<FormResponse> addToCart(@Valid @RequestBody ProductFormData productFormData, BindingResult bindingResult){
        FormResponse response = new FormResponse();
        List<String> messages = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            response.setMessageList(convertBindingResultToMessageList(bindingResult));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            cartService.addItemToCart(productFormData.getId(), productFormData.getQuantity());
        } catch (DataAccessException e) {
            messages.add(context.getMessage("cart.product.add.cant", null, Locale.getDefault()));
            response.setMessageList(messages);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        messages.add(context.getMessage("cart.product.add.ok", null, Locale.getDefault()));
        response.setMessageList(messages);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<FormResponse> handleHttpMessageNotReadableException (HttpMessageNotReadableException exception){
        FormResponse formResponse = new FormResponse();
        List<String> messages = new ArrayList<>();
        messages.add(context.getMessage("cart.product.add.quantity.bad", null, Locale.getDefault()));
        formResponse.setMessageList(messages);
        return new ResponseEntity<>(formResponse, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/shop/deleteFromCart", method = RequestMethod.POST)
    public @ResponseBody FormResponse deleteFromCart (@RequestBody CartItemIdDTO idDTO){
        FormResponse response = new FormResponse();
        List<String> messages = new ArrayList<>();
        cartService.deleteCartItem(idDTO.getId());
        messages.add(context.getMessage("cart.product.delete.ok", null, Locale.getDefault()));
        response.setMessageList(messages);
        return response;
    }

    @RequestMapping(value = "/shop/updateCart", method = RequestMethod.POST)
    public String updateCart (@Valid @ModelAttribute("cartFormData")CartFormData cartFormData, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("cart", cartService.getCart());
            return "cart";
        }
        cartService.updateCart(convertCartFormDataToMap(cartFormData));
        model.addAttribute("cart", cartService.getCart());
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
        if (cartFormData.getCartItemDTOs() == null){
            return new HashMap<>();
        }
        Map<Long, Long> cartModifier = new ConcurrentHashMap<>();
        for (CartItemDTO cartItemDto : cartFormData.getCartItemDTOs()) {
            cartModifier.put(cartItemDto.getId(), cartItemDto.getQuantity());
        }
        return cartModifier;
    }
}
