package home.zubarev.model.validation;

import home.zubarev.model.dto.OrderItemDTO;
import home.zubarev.model.formdata.CartFormData;
import home.zubarev.model.formdata.ProductFormData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Igor Zubarev on 12.09.2016.
 */
@Component
public class CartFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProductFormData.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        CartFormData cartFormData = (CartFormData) object;
        for (OrderItemDTO orderItemDTO : cartFormData.getOrderItemDTOs()) {
            if (orderItemDTO.getQuantity() != null){
                if (orderItemDTO.getQuantity() >100){
                    errors.reject("failInput" ,"Too much quantity");
                    return;
                }
                if (orderItemDTO.getQuantity() < 0){
                    errors.reject("failInput" ,"Too low quantity");
                    return;
                }
            }

        }

    }
}
