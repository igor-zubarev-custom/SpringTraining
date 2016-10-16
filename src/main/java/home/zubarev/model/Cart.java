package home.zubarev.model;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
    private Long id;
    private List<CartItem> cartItems;
    private BigDecimal totalPrice;
    private String firstName;
    private String lastName;
    private String deliveryAddress;
    private String contactPhone;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    public String getContactPhone() {
        return contactPhone;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Cart() {
    }

    public Cart(List<CartItem> cartItems, BigDecimal totalPrice, String firstName, String lastName, String deliveryAddress, String contactPhone) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.firstName = firstName;
        this.lastName = lastName;
        this.deliveryAddress = deliveryAddress;
        this.contactPhone = contactPhone;
    }
}
