package lesson30.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

    @RequestMapping(value = "/hello")
    public String sayHello(Model model) {
        model.addAttribute("message", "Hello world");
        List<String> stringList = Arrays.asList("one", "два", "three");
        model.addAttribute("strings", stringList);
        return "hello";
    }
}
