package com.exilesoft.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.exilesoft.demo.security.SecurityCheckAspect;
import com.exilesoft.demo.security.SecurityProxy;

public class JavaConfigTest {

    @Configuration
    public static class ExplicitConfig {

    	@Bean
    	public ApplicationInfo applicationInfo() throws Exception {
    		return (ApplicationInfo)new SecurityProxy(new ApplicationInfoImpl("public", "secret"), ApplicationInfo.class).getObject();
    	}

    	@Bean
        public Application application(ApplicationInfo applicationInfo) {
            return new Application();
        }
    }

    public static class ExplicitTest extends AbstractSecurityTest {
	    @Override
		protected ApplicationContext createContext() {
	        return new AnnotationConfigApplicationContext(ExplicitConfig.class);
	    }
    }

    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan("com.exilesoft.demo")
    public static class MagicConfig {

    	@Bean
    	public SecurityCheckAspect securityCheck() {
    		return new SecurityCheckAspect();
    	}

    	@Bean(name="username") public String username() {
    		return "public";
    	}

    	@Bean(name="password") public String password() {
    		return "secret";
    	}
    }

    public static class MagicTest extends AbstractSecurityTest {
	    @Override
		protected ApplicationContext createContext() {
	        return new AnnotationConfigApplicationContext(MagicConfig.class);
	    }
    }

}
