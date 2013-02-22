package com.exilesoft.demo;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class XmlConfigTest {

	public static class ExplicitTest extends AbstractSecurityTest {
	    @Override
		protected ApplicationContext createContext() {
	        return new ClassPathXmlApplicationContext("/com/exilesoft/demo/explicitContext.xml");
	    }
	}

	public static class PostProcessorTest extends AbstractSecurityTest {
		@Override
		protected ApplicationContext createContext() {
			return new ClassPathXmlApplicationContext("/com/exilesoft/demo/postProcessorContext.xml");
		}
	}

	public static class AopTest extends AbstractSecurityTest {
		@Override
		protected ApplicationContext createContext() {
			return new ClassPathXmlApplicationContext("/com/exilesoft/demo/aopContext.xml");
		}
	}

	public static class AutowireTest extends AbstractSecurityTest {
		@Override
		protected ApplicationContext createContext() {
			return new ClassPathXmlApplicationContext("/com/exilesoft/demo/autowireContext.xml");
		}
	}

	public static class ComponentScanTest extends AbstractSecurityTest {
		@Override
		protected ApplicationContext createContext() {
			return new ClassPathXmlApplicationContext("/com/exilesoft/demo/componentScanContext.xml");
		}
	}
}
