package com.newtouch.blockchain.security.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSONObject;
import com.newtouch.blockchain.entity.WalletAccount;
import com.newtouch.blockchain.security.vo.CustomUserDetails;
import com.newtouch.blockchain.service.JwtService;
import com.newtouch.blockchain.service.WalletAccountService;
import com.newtouch.blockchain.util.HttpClientUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	

	@Autowired
	private JwtService jwtService;
	@Autowired
	private WalletAccountService walletAccountService;
	@Autowired
	private OAuth2ResourceServerParam oAuth2ResourceServerParam;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String access_token = StringUtils.EMPTY;
		if (session.getAttribute("access_token") != null) {
			access_token = (String) session.getAttribute("access_token");
		}
		String token = getAccessToken(request);
		if (StringUtils.isNotBlank(token)) {
			if (SecurityContextHolder.getContext().getAuthentication() != null && access_token.equals(token)) {
				filterChain.doFilter(request, response);
				return;
			}
			try {
				Jwt jwt = jwtService.parseToken(token);
				if (jwt != null) {
					CustomUserDetails details = parseJwtToUserDetails(jwt);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							details, null, details.getGrantedAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authentication);
					session.setAttribute("CURRENT_USER", details);
					session.setAttribute("access_token", token);
					saveAccount(details.getAccount());
				}
			} catch (Exception e) {
				log.error("{}", e);
				SecurityContextHolder.getContext().setAuthentication(null);
			}
		}
		filterChain.doFilter(request, response);
	}
	/**
	 * 从JWT中组装CustomUserDetails
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	private CustomUserDetails parseJwtToUserDetails(Jwt jwt) throws Exception {
		CustomUserDetails details = new CustomUserDetails();
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (String authority : jwt.getClaimAsStringList("authorities")) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority));
		}
		WalletAccount account = new WalletAccount();
		String userId = jwt.getClaimAsString("user_id");
		account.setId(userId);
		account.setUsername(jwt.getClaimAsString("sub"));
		account.setName(jwt.getClaimAsString("display_name"));
		if (StringUtils.isNotBlank(userId)) {
			String result = getAccountAddress(userId);
			if (StringUtils.isNotBlank(result)) {
				account.setAddress(result);
			} else {
				throw new RuntimeException("获取用户钱包失败");
			}
		}
		details.setAccount(account);
		details.setGrantedAuthorities(grantedAuthorities);
		return details;
	}
	/**
	 * 保存登录用户信息
	 * @param account
	 */
	private void saveAccount(WalletAccount account) {
		WalletAccount wallet = walletAccountService.findByUsername(account.getUsername());
		if (wallet == null) {
			walletAccountService.save(account);
		} else {
			account.setFirstInsert(wallet.getFirstInsert());
			account.setDeleted(wallet.getDeleted());
			walletAccountService.update(account);
		}
	}

	/**
	 * 获取cookie中的access_token
	 * 
	 * @param request
	 * @return
	 */
	private String getAccessToken(HttpServletRequest request) {
		String access_token = StringUtils.EMPTY;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if ("access_token".equals(cookie.getName())) {
					access_token = cookie.getValue();
				}
			}
		}
		return access_token;
	}

	private String getAccountAddress(String userId) throws Exception {
		String address = StringUtils.EMPTY;
		Map<String, Object> params = new HashMap<String, Object>();
		//
//		userId = "fef7da05-40b4-4502-bc73-2f37c5be521c";
		params.put("id", userId);
		String result = HttpClientUtils.doGet(oAuth2ResourceServerParam.getAccountUrl(), params);
		if (StringUtils.isNotBlank(result)) {
			JSONObject json = JSONObject.parseObject(result);
			if (json.getInteger("err") == 0) {
				address = json.getString("msg");
			}
		}
		return address;
	}

}
