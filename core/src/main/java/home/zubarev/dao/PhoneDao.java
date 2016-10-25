package home.zubarev.dao;

import home.zubarev.model.Phone;
import org.springframework.jdbc.InvalidResultSetAccessException;

import java.sql.SQLException;
import java.util.List;

public interface PhoneDao {
    Phone getPhone(Long id);
    void save (Phone phone);
    List<Phone> getPhones() throws InvalidResultSetAccessException;
    void close();
}
