package com.newtouch.blockchain.security.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class OAuth2ResourceServerParam {
	@Value("${authentication.server.url.login}")
	private String loginUrl;
	
	@Value("${authentication.server.url.account}")
	private String accountUrl;
	
	@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
	private String jwkSetUri;
}
