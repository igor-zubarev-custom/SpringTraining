package home.zubarev.web.formdata;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductFormData {
    private Long id;
//    @NotNull(message = "cart.product.add.quantity.null")
//    @Min(value = 1, message = "cart.product.add.quantity.min")
//    @Max(value = 1000, message = "cart.product.add.quantity.max")
    @NotNull
    @Min(value = 1)
    @Max(value = 1000)
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
