package com.newtouch.blockchain.security.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
@Component
public class OAuth2LogoutSuccessHandler implements LogoutSuccessHandler{
	@Autowired
	private OAuth2ResourceServerParam oAuth2ResourceServerParam;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if ("access_token".equals(cookie.getName()) || "refresh_token".equals(cookie.getName())) {
					cookie.setValue("");
					cookie.setDomain("stage.servicechain.newtouch.com");
					cookie.setMaxAge(0); 
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
		response.sendRedirect(oAuth2ResourceServerParam.getLoginUrl());
	}

}
