package home.zubarev.web.formdata;

import java.math.BigDecimal;

public class CartInfo {
    private Long quantity;
    private BigDecimal price;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
