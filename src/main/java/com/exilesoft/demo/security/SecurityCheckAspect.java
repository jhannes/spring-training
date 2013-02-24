package com.exilesoft.demo.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SecurityCheckAspect {

	@Before("execution(* com.exilesoft.foo.*.*(..)) && @annotation(requiresLogin)")
	public void checkAccess(@SuppressWarnings("unused") RequiresLogin requiresLogin) {
		if (UserContext.getUser() != null) {
			throw new RuntimeException("Access denied");
		}
	}

}
