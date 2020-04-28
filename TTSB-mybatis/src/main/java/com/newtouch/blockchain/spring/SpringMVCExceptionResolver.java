package com.newtouch.blockchain.spring;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.alibaba.fastjson.JSON;
import com.newtouch.blockchain.exception.SystemException;

import cn.kklazy.mvc.support.CommonResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @Package com.newtouch.blockchain.spring
 * @ClassName: SpringMVCExceptionResolver
 */
@Slf4j
public class SpringMVCExceptionResolver extends SimpleMappingExceptionResolver {
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		
		log.error("exception: {}", ex);
		
		// Expose ModelAndView for chosen error view.
		String viewName = determineViewName(ex, request);
		ModelAndView mv = new ModelAndView();
		if (viewName != null) {// JSP格式返回
			if (!(request.getHeader("accept").indexOf("application/json") > -1 
					|| (request.getHeader("X-Requested-With") != null 
						&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1
						)
				 )
				) {
				// 如果不是异步请求
				// Apply HTTP status code for error views, if specified.
				// Only apply it if we're processing a top-level request.
				Integer statusCode = determineStatusCode(request, viewName);
				if (statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode);
				}
				return getModelAndView(viewName, ex, request);
			} else {// 异步请求,JSON格式返回
				
				resolveExceptionByJson(mv, request, response, ex);
				
				return mv;
			}
		} else {//JSON
			
			resolveExceptionByJson(mv, request, response, ex);
			
			return mv;
		}
	}
	
	/**
	 * 异常以JSON返回
	 * @param mv
	 * @param response
	 * @param ex
	 * @param msg
	 */
	private void resolveExceptionByJson(ModelAndView mv, HttpServletRequest request, 
			HttpServletResponse response, Exception ex){
		
		Integer statusCode = response.getStatus();
		
		CommonResponse res = new CommonResponse();
		if (statusCode != HttpStatus.OK.value()) {
			res.setCode(statusCode.toString());
		} else {
			if (ex instanceof SystemException) {
				res.setCode(((SystemException)ex).getMessageCode());
			} else {
				res.setCode("error");
			}
		}
		
		try {
			
			prepareResponse(response);
			
			response.getWriter().write(JSON.toJSONString(res));
		} catch (IOException e) {
			log.error("connection to client error", e);
		}
		
		if (log.isDebugEnabled()) {
			log.debug("{}", ex);
		}
	}
	
	/**
	 * 设置response
	 * @param response
	 */
	private void prepareResponse(HttpServletResponse response) {
		response.setStatus(HttpStatus.OK.value());
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
        response.addDateHeader("Expires", 1L);
	}
}
