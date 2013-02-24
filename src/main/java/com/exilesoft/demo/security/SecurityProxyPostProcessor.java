package com.exilesoft.demo.security;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class SecurityProxyPostProcessor implements BeanPostProcessor {


    /**
     * The post processor can replace any bean it likes with another object
     * (as long at that object fullfills the same interface, or there may be trouble).
     * In our case, replace all beans which has an interface with a method with @RequiresLogin
     * with a SecurityProxy.
     */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	public Class<?> getInterfaceRequiringLogin(Class<? extends Object> beanClass) {
		for (Class<?> beanInterface : beanClass.getInterfaces()) {
			for (Method method : beanInterface.getMethods()) {
				if (method.getAnnotation(RequiresLogin.class) != null)
					return beanInterface;
			}
		}
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}


}
