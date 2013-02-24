package com.exilesoft.demo;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleXmlConfigTest {

	private final ApplicationContext ctx = new ClassPathXmlApplicationContext("/com/exilesoft/demo/simpleContext.xml");

	private final UserManager application = ctx.getBean(UserManager.class);

	@Test
	public void shouldFindPassword() {
	    assertThat(application.getPassword()).isEqualTo("secret");
	}

}