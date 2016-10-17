package home.zubarev.dao;

import home.zubarev.model.Cart;

import java.util.List;

public interface OrderDao {
    Cart get (Long id);
    void save (Cart cart);
    List<Cart> findAll();
    void close();
}
