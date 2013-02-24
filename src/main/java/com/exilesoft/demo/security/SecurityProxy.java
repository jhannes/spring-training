package com.exilesoft.demo.security;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

public class SecurityProxy implements FactoryBean<Object> {
    private Object targetObject;
    private Class<?> targetInterface;

    public SecurityProxy() {

    }

    public void setTargetInterface(Class<?> targetInterface) {
        this.targetInterface = targetInterface;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public SecurityProxy(Object object, Class<?> targetInterface) {
        this.targetObject = object;
        this.targetInterface = targetInterface;
    }

    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(targetInterface.getClassLoader(), new Class[] { targetInterface }, createHandler());
    }

    private InvocationHandler createHandler() {
        return new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if (!userIsAuthorized(method))
					throw new RuntimeException("Unauthorized user");

                return method.invoke(targetObject, args);
            }

        };
    }

    private boolean userIsAuthorized(Method method) {
    	RequiresLogin requiresLogin = method.getAnnotation(RequiresLogin.class);
		return requiresLogin == null || requiresLogin.value().equals(UserContext.getUser());
    }

    @Override
    public Class<?> getObjectType() {
        return targetInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}