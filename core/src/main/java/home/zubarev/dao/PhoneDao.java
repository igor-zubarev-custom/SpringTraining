package home.zubarev.dao;

import home.zubarev.model.Phone;

import java.util.List;

public interface PhoneDao {
    Phone get(Long id);
    void save (Phone phone);
    List<Phone> findAll();
    void close();
}
