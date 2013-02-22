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
    	public ApplicationInfo applicationInfo() throws Exception {
    		return new ApplicationInfoImpl("public", "secret");
    	}

    	@Bean
        public Application application() {
            return new Application();
        }
	}


	private final ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

	private final Application application = ctx.getBean(Application.class);

	@Test
	public void shouldFindPassword() {
	    assertThat(application.getPassword()).isEqualTo("secret");
	}

}