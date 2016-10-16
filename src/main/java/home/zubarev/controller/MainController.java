package home.zubarev.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Igor Zubarev on 29.08.2016.
 *
 * TODO: every page must go to a separate controller (?)
 * The following controllers names are standard for ecommerce:
 * ProductListController
 * ProductDetailsController
 * CartController
 * OrderController
 * OrderConfirmationController
 *
 * Most of the time a customer works with a cart. Cart is stored in HTTP serssion.
 * During order placement a cart is transformed into an Cart and persisted into the DB.
 *
 * Rename all the classes according to this rules please.
 * TODO:
 * +1) SPLIT Main controller
 * 2) multimodule project
 * +3) cart service session scope
 * +4) rename order -> cart
 * +5) http response status 400
 * +6) fix add2cart javascript
 * +7) spring tags on cart page + error per cart item
 * +8) define DB connection in project.properties
 */
@Controller
public class MainController {
    @RequestMapping(value = "/")
    public String home(){
        return "home";
    }
}
