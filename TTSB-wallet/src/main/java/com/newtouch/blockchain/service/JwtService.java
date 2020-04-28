package com.newtouch.blockchain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class JwtService {
	@Autowired
	private JwtDecoder jwtDecoder;
	
	public Jwt parseToken(String token) throws JwtException{
		Jwt jwt = jwtDecoder.decode(token);
		return jwt;
	}
	
	
	public static void main(String[] args) {
		JwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri("http://stage.servicechain.newtouch.com/api/authorization/.well-known/jwks.json").build();;
		Jwt jwt = jwtDecoder.decode("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ5YWxpbi56aGFuZ0BuZXd0b3VjaC5jb20iLCJ1c2VyX2lkIjoiYzRiNDViYjgtMmE4ZS00NWQ4LWJjNmYtOGFhZTFjZDQ4MmY1Iiwic2NvcGUiOlsiQW55Il0sImRpc3BsYXlfbmFtZSI6IuW8oOS6muaelyIsImV4cCI6MTU4NTAzMzE0MCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjIyYjY3ZDMwLWUxODEtNDNiZS1hZmY3LTRkNGI4NWVjOTY2MiIsImNsaWVudF9pZCI6InNlcnZpY2VjaGFpbiJ9.gLrR4Xk019m-c9yA-_2aKp3XeupxrvbnrXEsgIAaoSagii8HGCqsCbkbmeBBt95PKDXQc2giBuIYvhO8Tx6OdgaJ31NcE6BxXEy-d2FUe1RZVZKkwguiEQ3aaPUm2WjcJcu_yUTLm9_zmIl0WtyEwB4RQ4u7MDG7-FR6CR57CDGFELGSky2-iLQx1KeSMTr_oQjucfacDWXGxJZuRtu8pzCq7WuCfdYIrjfNhLKmJN392YsR1pUJa5HX9aSVr-rGlkROTvN0oEOCFJBzFCDUWfDfhVmE1hdqcyGw5PPD5fqHo6rS9dPuXPvNtOu6vAZvTFOfozTrSahrECDFGqZfbQ");
		System.out.println(JSON.toJSONString(jwt));
	}
}
