package service.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import service.user.UserService;

@Component
public class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {
    private Logger logger = LoggerFactory.getLogger(ContextRefreshListener.class);
    @Autowired
    private  ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        UserService userService = (UserService) applicationContext.getBean("getUserService");
        logger.info("userService again");
        userService.addUser("contextlogin", "contextPassword", null, null);
    }
}
