package home.zubarev.model.dto;


/**
 * Created by Igor Zubarev on 19.09.2016.
 */
public class OrderItemDTO {
    private Long id;
    private Long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
