package com.exilesoft.demo.security;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class SecurityProxyPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
	    Class<?> interfaceRequiringLogin = getInterfaceRequiringLogin(bean.getClass());
	    if (interfaceRequiringLogin != null) {
	        return new SecurityProxy(bean, interfaceRequiringLogin);
	    }
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
