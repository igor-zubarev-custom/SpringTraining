package home.zubarev.service;

import home.zubarev.model.Order;
import home.zubarev.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Igor Zubarev on 06.09.2016.
 */
public class OrderService {
    Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getAllItems(){
        Order order = getOrder();
        Long quantity = 0L;
        System.out.println(order);
        if (order != null){
            List<OrderItem> orderItems = order.getOrderItems();
            if (orderItems != null){
                for (OrderItem orderItem : orderItems) {
                    quantity += orderItem.getQuantity();
                }
                return quantity;
            }
            return quantity;
        }
        return quantity;
    }

    public BigDecimal getOrderPrice(){
        Order order = getOrder();
        BigDecimal orderPrice = BigDecimal.ZERO;
        System.out.println(order);
        if (order != null){
            List<OrderItem> orderItems = order.getOrderItems();
            if (orderItems != null){
                for (OrderItem orderItem : orderItems) {
                    Long quantity = orderItem.getQuantity();
                    BigDecimal price = orderItem.getPhone().getPrice();
                    orderPrice = orderPrice.add(price.multiply(BigDecimal.valueOf(quantity)));
                }
                return orderPrice;
            }
            return orderPrice;
        }
        return orderPrice;
    }
}
