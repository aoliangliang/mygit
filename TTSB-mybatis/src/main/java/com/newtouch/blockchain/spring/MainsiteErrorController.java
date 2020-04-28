package com.newtouch.blockchain.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.kklazy.mvc.support.CommonResponse;

/**
 * 
 * @Package com.newtouch.blockchain.spring
 * @ClassName: MainsiteErrorController
 */
@RestController
public class MainsiteErrorController implements ErrorController{

	private static final String ERROR_PATH = "/error";
	
	@RequestMapping(value=ERROR_PATH)
	public CommonResponse handleError(HttpServletRequest request, HttpServletResponse response) {
		CommonResponse res = new CommonResponse();
		
		res.setResult(false);
		res.setCode(String.valueOf(response.getStatus()));
		response.setStatus(HttpStatus.OK.value());
		
		return res;
	}
	
	/**
	 * @return
	 * @see org.springframework.boot.autoconfigure.web.ErrorController#getErrorPath()
	 */
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}
