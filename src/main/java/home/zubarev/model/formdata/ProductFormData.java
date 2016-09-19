package home.zubarev.model.formdata;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Igor Zubarev on 05.09.2016.
 */
public class ProductFormData {
    @NotNull
    private Long id;
    @NotNull(message = "The quantity must be specified")
    @Min(value = 1, message = "The quantity mus be greater then 0")
    @Max(value = 1000, message = "The quantity must be less then 1000")
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
