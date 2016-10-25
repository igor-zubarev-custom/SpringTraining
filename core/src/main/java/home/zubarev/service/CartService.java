package home.zubarev.service;

import home.zubarev.dao.PhoneDao;
import home.zubarev.model.Cart;
import home.zubarev.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {
    @Autowired
    @Resource(name = "phoneJdbcTemplateDaoImpl")
    private PhoneDao phoneDao;
    @Autowired
    private Cart cart;

    public Long getTotalQuantity(){
        Long quantity = 0L;
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            quantity += cartItem.getQuantity();
        }
        return quantity;
    }

    public BigDecimal getCartPrice(){
        BigDecimal orderPrice = BigDecimal.ZERO;
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            Long quantity = cartItem.getQuantity();
            BigDecimal price = cartItem.getPhone().getPrice();
            orderPrice = orderPrice.add(price.multiply(BigDecimal.valueOf(quantity)));
        }
        return orderPrice;
    }

    public boolean deleteCartItem(Long id){
        List<CartItem> cartItems = cart.getCartItems();
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getId().equals(id)){
                cartItems.remove(i);
                return true;
            }
        }
        return false;
    }

    public void addItemToCart(Long id, Long quantity) {
        List<CartItem> cartItems = cart.getCartItems();
        boolean isExist = false;
        int index = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getId().equals(id)){
                isExist = true;
                index = i;
            }
        }
        CartItem cartItem = null;
        if (isExist){
            cartItem = cartItems.get(index);
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }else {
            cartItem = createCartItem(id, quantity);
            cartItems.add(cartItem);
        }

    }

    public void updateCart(Map<Long, Long> cartItemsModifier){
        List<CartItem> cartItems = cart.getCartItems();
        for (Map.Entry<Long, Long> entry : cartItemsModifier.entrySet()) {
            for (int i = 0; i < cartItems.size(); i++) {
                if (cartItems.get(i).getId().equals(entry.getKey())){
                    if (entry.getValue().equals(0)){
                        cartItems.remove(i);
                        break;
                    }else {
                        cartItems.get(i).setQuantity(entry.getValue());
                    }
                }
            }
        }
    }

    public CartItem createCartItem(Long id, Long quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setPhone(phoneDao.getPhone(id));
        cartItem.setQuantity(quantity);
        return cartItem;
    }
}
