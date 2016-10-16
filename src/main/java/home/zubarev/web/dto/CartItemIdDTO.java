package home.zubarev.web.dto;

import javax.validation.constraints.NotNull;

public class CartItemIdDTO {
    @NotNull(message = "The ID must be specified")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
