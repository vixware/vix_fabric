package com.vix.common.security.util.extuserdetail;

public interface VixUserDetailsService {

	/**
	 * 自定义参数验证
	 * @param username
	 * @param password String password,
	 * @param tenantId
	 * @return
	 * @throws UsernameNotFoundException
	 */
	//UserDetails loadUserByUsername(String username, String tenantId) throws UsernameNotFoundException;
}
