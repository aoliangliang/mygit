package com.newtouch.blockchain.security.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.newtouch.blockchain.entity.WalletAccount;

import lombok.Getter;
import lombok.Setter;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = -7835930457272138805L;
	
	public static final String ROLE = "ROLE_USER";
	
	private Boolean accountNonExpired = Boolean.TRUE;
	private Boolean accountNonLocked  = Boolean.TRUE;
	private Boolean credentialsNonExpired = Boolean.TRUE;
	
	@Getter
	@Setter
	private WalletAccount account;
	
	@Getter
	@Setter
	private Collection<GrantedAuthority> grantedAuthorities;
	
	/**
	 * 
	 */
	public CustomUserDetails() {
		
	}
	
	/**
	 * @param user
	 */
	public CustomUserDetails(WalletAccount account) {
		this.account = account;
	}

	/**
	 * 控制当前用户的角色信息
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority(CustomUserDetails.ROLE));
		return grantedAuthorities;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return account.getPassword();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return account.getUsername();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return !account.getDeleted();
	}

}
