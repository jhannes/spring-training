package com.exilesoft.demo;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class SimpleJavaConfigTest {

	@Configuration
	public static class Config {

    	@Bean
        public UserManager application() {
            return new UserManager();
        }
	}


	private final ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

	private final UserManager application = ctx.getBean(UserManager.class);

	@Test
	public void shouldFindPassword() {
	    assertThat(application.getPassword()).isEqualTo("secret");
	}

}