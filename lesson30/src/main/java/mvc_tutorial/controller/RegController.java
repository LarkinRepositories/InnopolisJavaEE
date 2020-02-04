package mvc_tutorial.controller;

import mvc_tutorial.db.model.User;
import mvc_tutorial.service.RegistrationService;
import mvc_tutorial.service.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/reg")
public class RegController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegController.class);

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView renderRegistrationForm() {
        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("reg");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView userRegistration(@ModelAttribute(name="userForm") @Validated UserDto userDto,
                                         BindingResult result, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.setViewName("reg");
            modelAndView.addObject("result", result);
            return modelAndView;
        }

        registrationService.regUser(userDto);
        return modelAndView;
    }

    @ModelAttribute
    public User persistUser() {
        return new User();
    }
}
