package home.zubarev.dao;

import home.zubarev.model.Phone;

import java.sql.SQLException;
import java.util.List;

public interface PhoneDao {
    Phone getPhone(Long id) throws SQLException;
    void save (Phone phone);
    List<Phone> getPhones() throws SQLException;
    void close();
}
