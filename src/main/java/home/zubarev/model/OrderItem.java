package home.zubarev.model;

/**
 * Created by Igor Zubarev on 29.08.2016.
 */
public class OrderItem {
    private Long id;
    private Phone phone;
    private Long quantity;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Phone getPhone() {
        return phone;
    }
    public void setPhone(Phone phone) {
        this.phone = phone;
    }
    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public OrderItem() {
    }

    public OrderItem(Phone phone, Long quantity) {
        this.phone = phone;
        this.quantity = quantity;
    }
}
