package home.zubarev.web.formdata;

import home.zubarev.model.dto.OrderItemDTO;

import java.util.List;

/**
 * Created by Igor Zubarev on 19.09.2016.
 * TODO: separate model from web stuff (+)
 * I propose to move this class to home.zubarev.web.formdata package
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
