package home.zubarev.service;

import home.zubarev.web.formdata.CartFormData;
import home.zubarev.model.Order;
import home.zubarev.model.OrderItem;
import home.zubarev.model.dto.OrderItemDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Zubarev on 06.09.2016.
 */
@Service
public class OrderService {
    // multiple app users share the same order
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

    public Long getAllItems(Order order){
        setOrder(order);
        return getAllItems();
    }

    public BigDecimal getOrderPrice(){
        Order order = getOrder();
        BigDecimal orderPrice = BigDecimal.ZERO;
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

    public BigDecimal getOrderPrice(Order order){
        setOrder(order);
        return getOrderPrice();
    }

    public boolean deleteOrderItem(Order order, Long id){
        List<OrderItem> orderItems = order.getOrderItems();
        for (int i = 0; i < orderItems.size(); i++) {
            if (orderItems.get(i).getId() == id){
                orderItems.remove(i);
                return true;
            }
        }
        return false;
    }

    public void addOrderItem(Order order, OrderItem orderItem){
        List<OrderItem> orderItems = order.getOrderItems();
        if (orderItems != null){
            orderItems.add(orderItem);
        }else {
            orderItems = new ArrayList<>();
            orderItems.add(orderItem);
            order.setOrderItems(orderItems);
        }
    }

    public void updateOrder(Order order, CartFormData cartFormData){
        if (order != null) {
            List<OrderItem> orderItems = new ArrayList<>();
            for (OrderItemDTO orderItemDTO : cartFormData.getOrderItemDTOs()) {
                Long id = orderItemDTO.getId();
                Long quantity = orderItemDTO.getQuantity();
                if (id != null && quantity != null) {
                    for (OrderItem orderItem : order.getOrderItems()) {
                        if (orderItem.getId() == id) {
                            if (quantity != 0) {
                                orderItem.setQuantity(quantity);
                                orderItems.add(orderItem);
                            }
                        }
                    }

                }
            }
            order.setOrderItems(orderItems);
        }
    }
}
