package com.exilesoft.demo.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SecurityCheckAspect {

	@Before("execution(* com.exilesoft.demo.*.*(..)) && @annotation(requiresLogin)")
	public void checkAccess(RequiresLogin requiresLogin) {
		if (!requiresLogin.value().equals(UserContext.getUser())) {
			throw new RuntimeException("Access denied");
		}
	}

}
