package com.vix.common.security.util.extprovider;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

//import com.vix.common.security.util.extuserdetail.VixUsernamePasswordAuthenticationToken;

public class VixLoginAuthenticationProvider extends	AbstractUserDetailsAuthenticationProvider {
	
	private PasswordEncoder passwordEncoder = new PlaintextPasswordEncoder();
	//private PasswordEncoder passwordEncoder = new StandardPasswordEncoder("vixnt");
			
	private SaltSource saltSource;
	
	//private VixUserDetailsService userDetailsService;
	private UserDetailsService userDetailsService;
	
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
            messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports",
                "Only UsernamePasswordAuthenticationToken is supported"));

        // Determine username
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();

        boolean cacheWasUsed = true;
        UserDetails user = this.getUserCache().getUserFromCache(username);

        if (user == null) {
            cacheWasUsed = false;

            try {
                //user = retrieveUser(username, (VixUsernamePasswordAuthenticationToken) authentication);
            	user = retrieveUser(username, (UsernamePasswordAuthenticationToken) authentication);
            } catch (UsernameNotFoundException notFound) {
                logger.debug("User '" + username + "' not found");

                if (hideUserNotFoundExceptions) {
                    throw new BadCredentialsException(messages.getMessage(
                            "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
                } else {
                    throw notFound;
                }
            }

            Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");
        }

        try {
        	getPreAuthenticationChecks().check(user);
            //additionalAuthenticationChecks(user, (VixUsernamePasswordAuthenticationToken) authentication);
        	 additionalAuthenticationChecks(user, (UsernamePasswordAuthenticationToken) authentication);
        } catch (AuthenticationException exception) {
            if (cacheWasUsed) {
                // There was a problem, so try again after checking
                // we're using latest data (i.e. not from the cache)
                cacheWasUsed = false;
                //user = retrieveUser(username, (VixUsernamePasswordAuthenticationToken) authentication);
                user = retrieveUser(username, (UsernamePasswordAuthenticationToken) authentication);
                getPreAuthenticationChecks().check(user);
                //additionalAuthenticationChecks(user, (VixUsernamePasswordAuthenticationToken) authentication);
                additionalAuthenticationChecks(user, (UsernamePasswordAuthenticationToken) authentication);
            } else {
                throw exception;
            }
        }

        getPreAuthenticationChecks().check(user);

        if (!cacheWasUsed) {
            this.getUserCache().putUserInCache(user);
        }

        Object principalToReturn = user;

        if (isForcePrincipalAsString()) {
            principalToReturn = user.getUsername();
        }
        return createSuccessAuthentication(principalToReturn, authentication, user);
    }
    
	/**
	 * 新的方法
	 * @param userDetails
	 * @param authentication
	 * @throws AuthenticationException
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Object salt = null;

        if (this.saltSource != null) {
            salt = this.saltSource.getSalt(userDetails);
        }

        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
        }

        String presentedPassword = authentication.getCredentials().toString();

        if (!passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
            logger.debug("Authentication failed: password does not match stored value");

            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
        }
    }

	 @Override
	protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)throws AuthenticationException {
	    	
	    	UserDetails loadedUser;
	    	 
	        try{
	            //String password = (String) authentication.getCredentials();
	            //String tenantId =(String) authentication.getTenantId();
	            //loadedUser = getUserDetailsService().loadUserByUsername(username, password);//自定义扩展参数
	            loadedUser = getUserDetailsService().loadUserByUsername(username);////自定义扩展参数
	        }catch (UsernameNotFoundException notFound){
	            throw notFound;
	        }catch (Exception repositoryProblem){
	            throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
	        }
	 
	        if (loadedUser == null){
	            throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
	        }
	        return loadedUser;
	    }
    
	
	
	
	
    protected void additionalAuthenticationChecksBak(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
       /* Object salt = null;

        if (this.saltSource != null) {
            salt = this.saltSource.getSalt(userDetails);
        }

        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
        }

        String presentedPassword = authentication.getCredentials().toString();

        if (!passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
            logger.debug("Authentication failed: password does not match stored value");

            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
        }*/
    	System.out.println("additionalAuthenticationChecks.....................");
    }

    @Override
	protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
    }

    
    
    protected final UserDetails retrieveUserBak(String username, UsernamePasswordAuthenticationToken authentication)throws AuthenticationException {
    	
    	/*UserDetails loadedUser;
    	 
        try{
            //String password = (String) authentication.getCredentials();
            String tenantId ="";// (String) authentication.getTenantId();
            //loadedUser = getUserDetailsService().loadUserByUsername(username, password);//自定义扩展参数
            loadedUser = getUserDetailsService().loadUserByUsername(username, tenantId);////自定义扩展参数
        }catch (UsernameNotFoundException notFound){
            throw notFound;
        }catch (Exception repositoryProblem){
            throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
 
        if (loadedUser == null){
            throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;*/
    	System.out.println("retrieveUser   null .....................");
    	return null;
    }

    /**
     * Sets the PasswordEncoder instance to be used to encode and validate passwords.
     * If not set, the password will be compared as plain text.
     * <p>
     * For systems which are already using salted password which are encoded with a previous release, the encoder
     * should be of type {@code org.springframework.security.authentication.encoding.PasswordEncoder}. Otherwise,
     * the recommended approach is to use {@code org.springframework.security.crypto.password.PasswordEncoder}.
     *
     * @param passwordEncoder must be an instance of one of the {@code PasswordEncoder} types.
     */
    public void setPasswordEncoder(Object passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");

        if (passwordEncoder instanceof PasswordEncoder) {
            this.passwordEncoder = (PasswordEncoder) passwordEncoder;
            return;
        }
    }

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public SaltSource getSaltSource() {
		return saltSource;
	}

	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
