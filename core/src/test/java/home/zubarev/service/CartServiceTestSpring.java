package home.zubarev.service;

import home.zubarev.model.Cart;
import home.zubarev.model.CartItem;
import home.zubarev.model.DeliveryInfo;
import home.zubarev.model.Phone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/CartServiceTestSpring-context.xml")
@WebAppConfiguration
public class CartServiceTestSpring {

    @Autowired
    private CartService cartService;

    @Before
    public void initializingTest(){
        Phone phone1 = new Phone();
        phone1.setId(1L);
        phone1.setPrice(BigDecimal.valueOf(100));
        Phone phone2 = new Phone();
        phone2.setId(2L);
        phone2.setPrice(BigDecimal.valueOf(200));
        CartItem cartItem1 = new CartItem(phone1, 1L);
        cartItem1.setId(phone1.getId());
        CartItem cartItem2 = new CartItem(phone2, 2L);
        cartItem2.setId(phone2.getId());
        List<CartItem> cartItems = new CopyOnWriteArrayList<>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        Cart cart = new Cart();
        cart.setCartItems(cartItems);
        cart.setTotalPrice(BigDecimal.valueOf(500));
        cart.setTotalQuantity(3L);
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setDeliveryPrice(BigDecimal.valueOf(5));
        cart.setDeliveryInfo(deliveryInfo);
        cartService.setCart(cart);
    }

    @Test(expected = NullPointerException.class)
    public void calculateTotalQuantity() throws Exception {
        cartService.calculateTotalQuantity();
        Assert.assertEquals(cartService.getCart().getTotalQuantity(), Long.valueOf(3));

        cartService.getCart().getCartItems().clear();
        cartService.calculateTotalQuantity();
        Assert.assertEquals(cartService.getCart().getTotalQuantity(), Long.valueOf(0));

        cartService.getCart().setCartItems(null);
        cartService.calculateTotalQuantity();
    }

    @Test(expected = NullPointerException.class)
    public void calculateCartPrice() throws Exception {
        cartService.calculateCartPrice();
        Assert.assertEquals(cartService.getCart().getTotalPrice(), BigDecimal.valueOf(500));

        cartService.getCart().getCartItems().clear();
        cartService.calculateCartPrice();
        Assert.assertEquals(cartService.getCart().getTotalPrice(), BigDecimal.ZERO);

        cartService.getCart().setCartItems(null);
        cartService.calculateCartPrice();

    }

    @Test(expected = NullPointerException.class)
    public void getTotalPrice() throws Exception {
        Assert.assertEquals(BigDecimal.valueOf(505), cartService.getTotalPrice());

        cartService.getCart().getDeliveryInfo().setDeliveryPrice(null);
        cartService.getTotalPrice();

    }

    @Test
    public void deleteCartItem() throws Exception {
        Assert.assertEquals(false, cartService.deleteCartItem(3L));
        Assert.assertEquals(true, cartService.deleteCartItem(1L));

    }

    @Test
    public void addItemToCart() throws Exception {
        cartService.addItemToCart(1L, 1L);
        Assert.assertEquals(Long.valueOf(4), cartService.getCart().getTotalQuantity());

        cartService.getCart().getCartItems().clear();
        cartService.addItemToCart(1L, 1L);
        Assert.assertEquals(Long.valueOf(1), cartService.getCart().getTotalQuantity());


    }

    @Test
    public void updateCart() throws Exception {
        Map<Long, Long> cartItemModifier = new HashMap<>();
        cartItemModifier.put(1L, 1L);
        cartItemModifier.put(2L, 1L);
        Cart cart = cartService.getCart();
        cartService.updateCart(cartItemModifier);
        Assert.assertEquals(Long.valueOf(2), cart.getTotalQuantity());

        cartItemModifier.put(2L, 0L);
        cartService.updateCart(cartItemModifier);
        Assert.assertEquals(Long.valueOf(1), cart.getTotalQuantity());
    }

    @Test
    public void createCartItem() throws Exception {
        CartItem testCartItem = cartService.createCartItem(2L, 1L);
        Assert.assertEquals(Long.valueOf(2), testCartItem.getId());
        Assert.assertEquals(Long.valueOf(1), testCartItem.getQuantity());
        Assert.assertEquals(Long.valueOf(2), testCartItem.getPhone().getId());

    }

    @Test
    public void clearCart() throws Exception {
        cartService.clearCart();
        Cart cart = cartService.getCart();
        Assert.assertEquals(0, cart.getCartItems().size());
        Assert.assertEquals(BigDecimal.ZERO, cart.getTotalPrice());
        Assert.assertEquals(Long.valueOf(0), cart.getTotalQuantity());
    }

}