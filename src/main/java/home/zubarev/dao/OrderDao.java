package home.zubarev.dao;

import home.zubarev.model.Order;

import java.util.List;

/**
 * Created by Igor Zubarev on 29.08.2016.
 */
public interface OrderDao {
    Order get (Long id);
    void save (Order order);
    List<Order> findAll();
    void close();
}
