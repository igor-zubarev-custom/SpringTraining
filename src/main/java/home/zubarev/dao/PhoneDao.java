package home.zubarev.dao;

import home.zubarev.model.Phone;

import java.util.List;

/**
 * Created by Igor Zubarev on 29.08.2016.
 */
public interface PhoneDao {
    Phone get(Long id);
    void save (Phone phone);
    List<Phone> findAll();
    void close();
}
