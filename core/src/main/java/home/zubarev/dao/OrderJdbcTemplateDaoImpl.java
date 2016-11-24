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

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                "comment, " +
                "status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
                ps.setString(10, order.getStatus());
                return ps;
            }
        }, keyHolder);
        Long orderId = keyHolder.getKey().longValue();
        saveOrderItems(order.getCartItems(), orderId);
        return orderId;
    }

    @Override
    public List<Order> getOrders() {
        String sql = "SELECT * FROM order_table";
        List<Order> orders = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows){
            Order order = new Order();
            order.setId(Long.valueOf((Integer)row.get("id")));
            order.setCartPrice(BigDecimal.valueOf((Double) row.get("cart_price")));
            order.setTotalQuantity(Long.valueOf((Integer)row.get("total_quantity")));
            order.setDeliveryPrice(BigDecimal.valueOf((Double) row.get("delivery_price")));
            order.setTotalPrice(BigDecimal.valueOf((Double) row.get("total_price")));
            order.setFirstName((String)row.get("first_name"));
            order.setLastName((String)row.get("last_name"));
            order.setDeliveryAddress((String)row.get("delivery_address"));
            order.setContactPhone((String)row.get("contact_phone"));
            order.setComment((String)row.get("comment"));
            order.setStatus((String)row.get("status"));
            order.setCartItems(getCartItemsForOrderId(order.getId()));
            orders.add(order);
        }

        return orders;
    }

    @Override
    public void updateOrder(Long orderId, String status) {
        String sql = "UPDATE order_table SET status=? WHERE id=?";
        jdbcTemplate.update(sql, new Object[]{status, orderId});
    }

    public List<CartItem> getCartItemsForOrderId(Long orderId){
        String sql = "SELECT " +
                "order_item.quantity, " +
                "phone.* " +
//                "phone.model " +
//                "phone.price " +
//                "phone.color " +
//                "phone.display_size " +
//                "phone.camera " +
//                "phone.length " +
//                "phone.width " +
                "FROM order_item INNER JOIN phone ON order_item.phone = phone.id " +
                "WHERE order_item.order_id = ?";

        List<CartItem> cartItems = jdbcTemplate.query(sql, new Object[]{orderId}, new RowMappers.CartItemRowMapper());
        return cartItems;
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
