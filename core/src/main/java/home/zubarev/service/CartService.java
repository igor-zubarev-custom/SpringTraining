package home.zubarev.service;

import home.zubarev.dao.PhoneDao;
import home.zubarev.model.Cart;
import home.zubarev.model.CartItem;
import home.zubarev.web.dto.CartItemDTO;
import home.zubarev.web.formdata.CartFormData;
import home.zubarev.web.formdata.ProductFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {
    // osa: private?
    @Autowired
    PhoneDao phoneDao;
    Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    // osa: getQuantity?
    public Long getAllItems(){
        Cart cart = getCart();
        Long quantity = 0L;
        if (cart != null){
            List<CartItem> cartItems = cart.getCartItems();
            if (cartItems != null){
                for (CartItem cartItem : cartItems) {
                    quantity += cartItem.getQuantity();
                }
                return quantity;
            }
            return quantity;
        }
        return quantity;
    }

    public Long getAllItems(Cart cart){
        setCart(cart);
        return getAllItems();
    }

    public BigDecimal getOrderPrice(){
        Cart cart = getCart();
        BigDecimal orderPrice = BigDecimal.ZERO;
        if (cart != null){
            List<CartItem> cartItems = cart.getCartItems();
            if (cartItems != null){
                for (CartItem cartItem : cartItems) {
                    Long quantity = cartItem.getQuantity();
                    BigDecimal price = cartItem.getPhone().getPrice();
                    orderPrice = orderPrice.add(price.multiply(BigDecimal.valueOf(quantity)));
                }
                return orderPrice;
            }
            return orderPrice;
        }
        return orderPrice;
    }

    public BigDecimal getOrderPrice(Cart cart){
        setCart(cart);
        return getOrderPrice();
    }

    public boolean deleteOrderItem(Cart cart, Long id){
        List<CartItem> cartItems = cart.getCartItems();
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getId() == id){
                cartItems.remove(i);
                return true;
            }
        }
        return false;
    }

    public void addOrderItem(Cart cart, CartItem cartItem){
        List<CartItem> cartItems = cart.getCartItems();
        if (cartItems != null){
            cartItems.add(cartItem);
        }else {
            cartItems = new ArrayList<>();
            cartItems.add(cartItem);
            cart.setCartItems(cartItems);
        }
    }

    // osa: service layer shall not depend on web layer
    public void updateOrder(Cart cart, CartFormData cartFormData){
        if (cart != null) {
            List<CartItem> cartItems = new ArrayList<>();
            for (CartItemDTO cartItemDTO : cartFormData.getCartItemDTOs()) {
                Long id = cartItemDTO.getId();
                Long quantity = cartItemDTO.getQuantity();
                if (id != null && quantity != null) {
                    for (CartItem cartItem : cart.getCartItems()) {
                        // osa: ERROR equals?
                        if (cartItem.getId() == id) {
                            if (quantity != 0) {
                                cartItem.setQuantity(quantity);
                                cartItems.add(cartItem);
                            }
                        }
                    }

                }
            }
            cart.setCartItems(cartItems);
        }
    }

    // osa: no servlet dependencies in services please
    public Cart getCart (HttpServletRequest request){
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        return cart;
    }

    public CartItem createCartItem(ProductFormData productFormData) {
        CartItem cartItem = new CartItem();
        cartItem.setId(IdGenerator.generateId());
        cartItem.setPhone(phoneDao.get(productFormData.getId()));
        cartItem.setQuantity(productFormData.getQuantity());
        return cartItem;
    }
}
