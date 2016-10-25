package home.zubarev.dao;

import home.zubarev.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneJdbcDaoImpl implements PhoneDao {

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Phone getPhone(Long id) throws InvalidResultSetAccessException {
        String sql = "SELECT * FROM phone WHERE id = ?";
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(new Integer(1), id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Phone phone = null;
            if (resultSet.next()){
                phone = createPhoneFromResultSet(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
            return phone;
        }catch (SQLException e){
            throw new InvalidResultSetAccessException(e);
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void save(Phone phone) {

    }

    @Override
    public List<Phone> getPhones() throws InvalidResultSetAccessException {
        String sql = "SELECT * FROM phone";
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Phone> resultList = new ArrayList<>();
            while (resultSet.next()) {
                Phone phone = createPhoneFromResultSet(resultSet);
                resultList.add(phone);
            }
            resultSet.close();
            preparedStatement.close();
            return resultList;
        }catch (SQLException e){
            throw new InvalidResultSetAccessException(e);
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Phone createPhoneFromResultSet(ResultSet resultSet) throws SQLException {
        Phone phone = new Phone();
        phone.setId(resultSet.getLong("id"));
        phone.setModel(resultSet.getString("model"));
        phone.setPrice(resultSet.getBigDecimal("price"));
        phone.setColor(resultSet.getString("color"));
        phone.setDisplaySize(resultSet.getString("display_size"));
        phone.setCamera(resultSet.getString("camera"));
        phone.setLength(resultSet.getInt("length"));
        phone.setWidth(resultSet.getInt("width"));
        return phone;
    }

    @Override
    public void close() {

    }
}
