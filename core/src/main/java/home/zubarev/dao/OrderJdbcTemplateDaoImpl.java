package home.zubarev.dao;

import home.zubarev.model.CartItem;
import home.zubarev.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class OrderJdbcTemplateDaoImpl implements OrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Long save(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO ORDER_TABLE("+
                "cart_price, " +
                "total_quantity, " +
                "delivery_price, " +
                "total_price, " +
                "first_name, " +
                "last_name, " +
                "delivery_address, " +
                "contact_phone, " +
                "comment) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setBigDecimal(1, order.getCartPrice());
                ps.setLong(2, order.getTotalQuantity());
                ps.setBigDecimal(3, order.getDeliveryPrice());
                ps.setBigDecimal(4, order.getTotalPrice());
                ps.setString(5, order.getFirstName());
                ps.setString(6, order.getLastName());
                ps.setString(7, order.getDeliveryAddress());
                ps.setString(8, order.getContactPhone());
                ps.setString(9, order.getComment());
                return ps;
            }
        }, keyHolder);
        Long orderId = keyHolder.getKey().longValue();
        saveOrderItems(order.getCartItems(), orderId);
        return orderId;
    }

    public void saveOrderItems(List<CartItem> orderItems, Long orderId){
        String sql = "INSERT INTO ORDER_ITEM (phone, quantity, order_id) VALUES (?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CartItem cartItem = orderItems.get(i);
                ps.setLong(1, cartItem.getPhone().getId());
                ps.setLong(2, cartItem.getQuantity());
                ps.setLong(3, orderId);
            }

            @Override
            public int getBatchSize() {
                return orderItems.size();
            }
        });
    }
}
