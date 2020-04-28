package com.newtouch.blockchain.security.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cn.kklazy.mvc.support.CommonResponse;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Autowired
	private OAuth2ResourceServerParam oAuth2ResourceServerParam;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		if (isAjaxRequest(request)) {
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			response.getWriter().print(JSON.toJSONString(CommonResponse.failed(StringUtils.EMPTY).setMessage("没有权限访问")));
			response.getWriter().flush();
		}else {
			response.sendRedirect(oAuth2ResourceServerParam.getLoginUrl());
		}
	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		return (request.getHeader("accept") != null && request.getHeader("accept").indexOf("application/json") != -1)
				|| (request.getHeader("X-Requested-With") != null
						&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") != -1);
	}
}
