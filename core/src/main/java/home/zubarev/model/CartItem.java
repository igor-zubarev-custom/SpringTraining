package home.zubarev.model;


import java.math.BigDecimal;

public class CartItem {
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

    public CartItem() {
    }

    public CartItem(Phone phone, Long quantity) {
        this.phone = phone;
        this.quantity = quantity;
        id = phone.getId();
    }

    public BigDecimal getPrice(){
        return phone.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
