package com.vix.common.security.util.extuserdetail;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

public class VixUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	/** VIX tenantId */
	private Object tenantId;

	// ~ Constructors
	// ===================================================================================================

	/**
	 * This constructor can be safely used by any code that wishes to create a
	 * <code>UsernamePasswordAuthenticationToken</code>, as the
	 * {@link #isAuthenticated()} will return <code>false</code>.
	 * 
	 */
	public VixUsernamePasswordAuthenticationToken(Object principal,Object credentials,Object tenantId) {
		super(principal, credentials);
		this.tenantId = tenantId;
		//setAuthenticated(false);
	}

	/**
	 * This constructor should only be used by
	 * <code>AuthenticationManager</code> or <code>AuthenticationProvider</code>
	 * implementations that are satisfied with producing a trusted (i.e.
	 * {@link #isAuthenticated()} = <code>true</code>) authentication token.
	 * 
	 * @param principal
	 * @param credentials
	 * @param authorities
	 */
	public VixUsernamePasswordAuthenticationToken(Object principal,
			Object credentials,
			Object tenantId,
			Collection<? extends GrantedAuthority> authorities) {
		
		super(principal, credentials,authorities);
		
		this.tenantId = tenantId;
		//super.setAuthenticated(true); // must use super, as we override
	}

	// ~ Methods
	// ========================================================================================================

	public Object getTenantId() {
		return tenantId;
	}

}
