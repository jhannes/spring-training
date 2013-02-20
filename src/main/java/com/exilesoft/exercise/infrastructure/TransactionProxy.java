package com.exilesoft.exercise.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.persistence.EntityManager;

public class TransactionProxy {

    @SuppressWarnings("unchecked")
    public static<T> T createProxy(final EntityManager entityManager, final T repo, Class<T> targetInterface) {
        return (T) Proxy.newProxyInstance(targetInterface.getClassLoader(), new Class[] { targetInterface }, new InvocationHandler() {
    
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                entityManager.getTransaction().begin();
                boolean commit = false;
                try {
                    Object result = method.invoke(repo, args);
                    commit = true;
                    return result;
                } finally {
                    if (commit)
                        entityManager.getTransaction().commit();
                    else
                        entityManager.getTransaction().rollback();
                }
            }
        });
    }

}
