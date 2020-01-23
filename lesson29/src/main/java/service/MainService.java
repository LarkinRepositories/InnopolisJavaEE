package service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import pojo.user.User;
import service.mobile.MobileService;
import service.user.UserService;
import service.user.UserServiceImpl;

@Component
public class MainService {
    private static UserService userService;

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext annotationConfigApplicationContext =
//                new AnnotationConfigApplicationContext()
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        userService = (UserService) applicationContext.getBean("getUserService");
//        userService = applicationContext.getBean(UserService.class);
        userService.addUser("login28", "password28", null, null);
    }
}
