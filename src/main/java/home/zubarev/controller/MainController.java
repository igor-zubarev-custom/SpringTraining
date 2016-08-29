package home.zubarev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Igor Zubarev on 29.08.2016.
 */
@Controller
public class MainController {
    @RequestMapping(value = "/")
    public String home(){
        return "home";
    }
}
