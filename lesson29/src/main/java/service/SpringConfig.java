//package service;
//
//import ConnectionManager.ConnectionManager;
//import ConnectionManager.ConnectionManagerJdbcImpl;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import service.mobile.MobileService;
//import service.mobile.MobileServiceImpl;
//import service.processor.BeanPostProcessorImpl;
//import service.user.UserServiceImpl;
//
//@Configuration
////@ComponentScan()
//public class SpringConfig {
//    private static final  ConnectionManager connectionManger = ConnectionManagerJdbcImpl.getInstance();
//
//    @Bean
//    public UserServiceImpl getUserService() {
//        return  new UserServiceImpl(connectionManger);
//    }
//
//    @Bean
//    public MobileService getMobileService() {
//        return new MobileServiceImpl(connectionManger);
//    }
//
//    @Bean
//    public BeanPostProcessor beanPostProcessor() {
//        return new BeanPostProcessorImpl();
//    }
//}
