package home.zubarev.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * TODO:
 * +1) SPLIT Main controller
 * 2) multimodule project
 * +3) cart service session scope
 * +4) rename order -> cart
 * +5) http response status 400
 * +6) fix add2cart javascript
 * +7) spring tags on cart page + error per cart item
 * +8) define DB connection in project.properties
 * ___________________________
 *
 */
@Controller
public class MainController {
    @RequestMapping(value = "/")
    public String home(){
        return "home";
    }
}
