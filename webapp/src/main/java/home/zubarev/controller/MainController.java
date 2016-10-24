package home.zubarev.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @RequestMapping(value = "/")
    public String home(){
        return "home";
    }
}
