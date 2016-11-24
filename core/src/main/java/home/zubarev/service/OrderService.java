package home.zubarev.service;

import home.zubarev.dao.OrderDao;
import home.zubarev.model.Cart;
import home.zubarev.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private static final String STATUS_WAITING = "WAITING FOR DELIVERY";
    private static final String STATUS_COMPLETED = "COMPLETED";

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
        order.setStatus(STATUS_WAITING);
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

    public Long placeOrder() {
        Long id = orderDao.save(order);
        deleteOrder();
        cartService.clearCart();
        return id;
    }

    public List<Order> getOrderList() {
        return orderDao.getOrders();
    }

    public void changeOrderStatus(Long orderId, int statusNumber) {
        System.out.println(statusNumber);
        String status = "";
        if (statusNumber == 0){
            status = STATUS_COMPLETED;
        }else if (statusNumber == 1){
            status = STATUS_WAITING;
        }
        orderDao.updateOrder(orderId, status);
    }
}
