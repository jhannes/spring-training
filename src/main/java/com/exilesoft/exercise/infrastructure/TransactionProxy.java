package com.exilesoft.exercise.infrastructure;

import java.io.Closeable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TransactionProxy {

    public static abstract class SimpleTransaction implements Closeable {

		private boolean commit;

		public final void setCommit() {
			this.commit = true;
		}

		@Override
		public final void close() {
			if (commit) commit();
			else rollback();
		}

		protected abstract void commit();
		protected abstract void rollback();

	}

	public interface SimpleTransactionManager {

		SimpleTransaction begin();

	}

	public static class HibernateTransactionManager implements SimpleTransactionManager {

		private final SessionFactory sessionFactory;

		public HibernateTransactionManager(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		@Override
		public SimpleTransaction begin() {
			final Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
			return new SimpleTransaction() {

				@Override
				protected void commit() {
					transaction.commit();
				}

				@Override
				protected void rollback() {
					transaction.rollback();
				}
			};
		}

	}

	public static class JpaTransactionManager implements SimpleTransactionManager {

		private final EntityManager entityManager;

		public JpaTransactionManager(EntityManager entityManager) {
			this.entityManager = entityManager;
		}

		@Override
		public SimpleTransaction begin() {
			final EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			return new SimpleTransaction() {

				@Override
				protected void commit() {
					transaction.commit();
				}

				@Override
				protected void rollback() {
					transaction.rollback();
				}
			};
		}

	}

    public static<T> T createProxy(final EntityManager entityManager, final T repo, Class<T> targetInterface) {
    	return createProxy(repo, targetInterface, new JpaTransactionManager(entityManager));
    }

    public static <T> T createProxy(final SessionFactory sessionFactory, final T repo, Class<T> targetInterface) {
		return createProxy(repo, targetInterface, new HibernateTransactionManager(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	private static <T> T createProxy(final T repo, Class<T> targetInterface, final SimpleTransactionManager mgr) {
		return (T) Proxy.newProxyInstance(targetInterface.getClassLoader(), new Class[] { targetInterface }, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try (SimpleTransaction tx = mgr.begin()) {
                    Object result = method.invoke(repo, args);
                    tx.setCommit();
                    return result;
                }
            }
        });
	}

}
