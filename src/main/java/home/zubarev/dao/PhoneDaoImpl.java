package home.zubarev.dao;

import home.zubarev.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneDaoImpl implements PhoneDao {

    // osa: spring JDBC template
    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Phone get(Long id) {
        String sql = "SELECT * FROM phone WHERE id = ?";
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(new Integer(1), id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Phone phone = null;
            if (resultSet.next()){
                phone = new Phone(
                    resultSet.getLong("id"),
                    resultSet.getString("model"),
                    resultSet.getBigDecimal("price"),
                    resultSet.getString("color"),
                    resultSet.getString("display_size"),
                    resultSet.getString("camera"),
                    resultSet.getInt("length"),
                    resultSet.getInt("width")
                );
            }
            resultSet.close();
            preparedStatement.close();
            return phone;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void save(Phone phone) {

    }

    @Override
    public List<Phone> findAll() {
        String sql = "SELECT * FROM phone";
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Phone> resultList = new ArrayList<>();
            while (resultSet.next()){
                Phone phone = new Phone(
                        // osa: code duplication
                        resultSet.getLong("id"),
                        resultSet.getString("model"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("color"),
                        resultSet.getString("display_size"),
                        resultSet.getString("camera"),
                        resultSet.getInt("length"),
                        resultSet.getInt("width")
                        );
                resultList.add(phone);
            }
            resultSet.close();
            preparedStatement.close();
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void close() {

    }
}
