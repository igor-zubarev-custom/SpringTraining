package home.zubarev.web.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CartItemDTO {
    @NotNull(message = "The ID must be specified")
    private Long id;
    @NotNull(message = "The quantity must be specified")
    @Min(value = 1, message = "Quantity must be greatest then 0")
    @Max(value = 1000, message = "Quantity must be less then 1000")
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
