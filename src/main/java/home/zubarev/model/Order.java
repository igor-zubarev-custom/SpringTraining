package home.zubarev.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Igor Zubarev on 29.08.2016.
 */
public class Order {
    private Long id;
    private List<OrderItem> orderItems;
    private BigDecimal totalPrice;
    private String firstName;
    private String lastName;
    private String deliveryAddress;
    private String contactPhone;
}
