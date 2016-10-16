package home.zubarev.web.formdata;

import home.zubarev.web.dto.CartItemDTO;

import javax.validation.Valid;
import java.util.List;

public class CartFormData {
    @Valid
    private List<CartItemDTO> cartItemDTOs;

    public List<CartItemDTO> getCartItemDTOs() {
        return cartItemDTOs;
    }

    public void setCartItemDTOs(List<CartItemDTO> cartItemDTOs) {
        this.cartItemDTOs = cartItemDTOs;
    }
}
