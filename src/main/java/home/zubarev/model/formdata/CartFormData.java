package home.zubarev.model.formdata;

import home.zubarev.model.dto.OrderItemDTO;

import java.util.List;

/**
 * Created by Igor Zubarev on 19.09.2016.
 */
public class CartFormData {
    private List<OrderItemDTO> orderItemDTOs;

    public List<OrderItemDTO> getOrderItemDTOs() {
        return orderItemDTOs;
    }

    public void setOrderItemDTOs(List<OrderItemDTO> orderItemDTOs) {
        this.orderItemDTOs = orderItemDTOs;
    }
}
