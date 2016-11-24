package home.zubarev.dao;

import home.zubarev.model.CartItem;
import home.zubarev.model.Phone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Igor Zubarev on 23.11.2016.
 */
public class RowMappers {
    public static final class CartItemRowMapper implements RowMapper{

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            CartItem cartItem = new CartItem();
            Phone phone = new Phone();
            cartItem.setQuantity(resultSet.getLong("quantity"));
            phone.setId(resultSet.getLong("id"));
            phone.setModel(resultSet.getString("model"));
            phone.setPrice(resultSet.getBigDecimal("price"));
            phone.setColor(resultSet.getString("color"));
            phone.setDisplaySize(resultSet.getString("display_size"));
            phone.setCamera(resultSet.getString("camera"));
            phone.setLength(resultSet.getInt("length"));
            phone.setWidth(resultSet.getInt("width"));
            cartItem.setId(phone.getId());
            cartItem.setPhone(phone);
            return cartItem;
        }
    }
}
