package com.exilesoft.demo.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SecurityCheckAspect {

    /**
     * Since aspects pointcuts are not type safe, it's easy to mistype
     * a class name or something...
     */
	@Before("execution(* com.exilesoft.foo.*.*(..)) && @annotation(requiresLogin)")
	public void checkAccess(@SuppressWarnings("unused") RequiresLogin requiresLogin) {
		if (UserContext.getUser() != null) {
			throw new RuntimeException("Access denied");
		}
	}

}
