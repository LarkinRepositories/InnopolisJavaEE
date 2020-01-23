package lesson30.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

    @RequestMapping(value = "/first")
    public String getIndex() {
        return "index";
    }


}
