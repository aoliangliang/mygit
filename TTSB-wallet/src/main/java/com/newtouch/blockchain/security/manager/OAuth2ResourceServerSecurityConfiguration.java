package com.newtouch.blockchain.security.manager;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;;
	@Autowired
	private OAuth2LogoutSuccessHandler oAuth2LogoutSuccessHandler;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private OAuth2ResourceServerParam oAuth2ResourceServerParam;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().formLogin().loginPage(oAuth2ResourceServerParam.getLoginUrl()).and()
		.logout().logoutUrl("/logout").logoutSuccessHandler(oAuth2LogoutSuccessHandler).and()
				.authorizeRequests(authorizeRequests -> authorizeRequests.antMatchers("/test/hello").permitAll()
						.anyRequest().authenticated())
				.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter())
				.decoder(jwtDecoder());
		http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
		http.headers().frameOptions().disable();
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	private JwtAuthenticationConverter jwtAuthenticationConverter() { 
		return new JwtAuthenticationConverter() {
			@Override
			protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
				Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
				for (String authority : jwt.getClaimAsStringList("authorities")) {
					grantedAuthorities.add(new SimpleGrantedAuthority(authority));
				}
				return grantedAuthorities;
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withJwkSetUri(oAuth2ResourceServerParam.getJwkSetUri()).build();
	}
}
