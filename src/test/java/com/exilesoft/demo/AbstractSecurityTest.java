package com.exilesoft.demo;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.exilesoft.demo.security.UserContext;

public abstract class AbstractSecurityTest {

	protected abstract ApplicationContext createContext();

	private final ApplicationContext ctx = createContext();

	private final Application application = ctx.getBean(Application.class);

	@Test
	public void shouldAllowAccessToAuthorizedUsers() {
		UserContext.setUser("Johannes");
	    assertThat(application.getPassword()).isEqualTo("secret");
	}

	@Test(expected = RuntimeException.class)
	public void shouldDenyAccessToUnidentifiedUsers() {
		UserContext.setUser(null);
	    application.getPassword();
	}

	@Test(expected = RuntimeException.class)
	public void shouldDenyAccessToUnauthorizedUsers() {
		UserContext.setUser("Thief");
		application.getPassword();
	}

	@Test
	public void shouldAllowAccessToUnrestrictedInformation() {
		UserContext.setUser(null);
	    application.getUsername();
	}

}