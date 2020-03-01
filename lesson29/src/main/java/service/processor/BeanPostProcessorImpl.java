package service.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPostProcessorImpl implements BeanPostProcessor {
    final Logger logger = LoggerFactory.getLogger(BeanPostProcessorImpl.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
       if (beanName.equals("getUserService")) {
           //some logic here
           logger.info("postProccessBeforeInitialization method works");
       }
       return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("postProccessAfterInitialization method works");
        return bean;
    }
}
