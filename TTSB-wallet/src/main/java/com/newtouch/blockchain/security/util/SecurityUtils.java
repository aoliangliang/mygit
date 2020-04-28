package com.newtouch.blockchain.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.newtouch.blockchain.security.vo.CustomUserDetails;

public class SecurityUtils {
	/**
	 * 获取当前登录用户的信息
	 * @return
	 */
	public static CustomUserDetails getUserDetails() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		if (securityContext == null) {
			return null;
		}
		Authentication authentication =securityContext.getAuthentication();
		if (authentication == null) {
			return null;
		}
		CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
		return details;
//		CustomUserDetails details = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
//				.getPrincipal();
		
	}
}
