package home.zubarev.model.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Igor Zubarev on 19.09.2016.
 */
public class OrderItemIdDTO {
    @NotNull(message = "The ID must be specified")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
