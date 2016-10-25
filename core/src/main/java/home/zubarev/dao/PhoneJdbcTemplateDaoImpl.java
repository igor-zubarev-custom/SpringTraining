package home.zubarev.dao;

import home.zubarev.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PhoneJdbcTemplateDaoImpl implements PhoneDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Phone getPhone(Long id) {
        String sql = "SELECT * FROM phone WHERE id = ?";
        Phone phone = jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Phone.class));
        return phone;
    }

    @Override
    public void save(Phone phone) {

    }

    @Override
    public List<Phone> getPhones() {
        String sql = "SELECT * FROM phone";
        List<Phone> phones = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Phone>(Phone.class));
        return phones;
    }

    @Override
    public void close() {

    }
}
