package home.zubarev.service;

import home.zubarev.dao.OrderDao;
import home.zubarev.model.Cart;
import home.zubarev.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderService {
    @Autowired
    private Cart cart;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderDao orderDao;
    private Order order;

    public Order createOrder(){
        Order order = new Order();
        order.setCartItems(cart.getCartItems());
        order.setCartPrice(cart.getTotalPrice());
        order.setTotalQuantity(cart.getTotalQuantity());
        order.setDeliveryPrice(cart.getDeliveryInfo().getDeliveryPrice());
        order.setTotalPrice(cartService.getTotalPrice());
        order.setFirstName(cart.getDeliveryInfo().getFirstName());
        order.setLastName(cart.getDeliveryInfo().getLastName());
        order.setDeliveryAddress(cart.getDeliveryInfo().getDeliveryAddress());
        order.setContactPhone(cart.getDeliveryInfo().getContactPhone());
        order.setComment(cart.getDeliveryInfo().getComment());
        setOrder(order);
        return order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void deleteOrder() {
        order = null;
    }

    public Long saveOrder() {
        return orderDao.save(order);
    }
}
