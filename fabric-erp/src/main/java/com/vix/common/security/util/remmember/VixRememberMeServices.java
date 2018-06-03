package com.vix.common.security.util.remmember;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.util.StringUtils;

@Deprecated
public class VixRememberMeServices extends TokenBasedRememberMeServices {

	private static final ThreadLocal<HttpServletRequest> requestHolder =new ThreadLocal<HttpServletRequest>();  
	
	//private VixUserDetailsService vixUserDetailsService;
	private UserDetailsService userDetailsService;
	
	public HttpServletRequest getContext(){
		return requestHolder.get();
	}
	
	public void setContext(HttpServletRequest context) {
		requestHolder.set(context);
	}
	
	@Override
    public void onLoginSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication successfulAuthentication) {
		
		setContext(request);
		
        String username = retrieveUserName(successfulAuthentication);
        String password = retrievePassword(successfulAuthentication);
        String tenantId = request.getParameter("tenantId");
        if(StringUtils.hasLength(tenantId)){
        	tenantId = "";
        }

        // If unable to find a username and password, just abort as TokenBasedRememberMeServices is
        // unable to construct a valid token in this case.
        if (!StringUtils.hasLength(username)) {
            logger.debug("Unable to retrieve username");
            return;
        }

        if (!StringUtils.hasLength(password)) {
            UserDetails user = getUserDetailsService().loadUserByUsername(username);
        	//UserDetails user = getVixUserDetailsService().loadUserByUsername(username, tenantId);
            password = user.getPassword();

            if (!StringUtils.hasLength(password)) {
                logger.debug("Unable to obtain password for user: " + username);
                return;
            }
        }

        int tokenLifetime = calculateLoginLifetime(request, successfulAuthentication);
        long expiryTime = System.currentTimeMillis();
        // SEC-949
        expiryTime += 1000L* (tokenLifetime < 0 ? TWO_WEEKS_S : tokenLifetime);

        String signatureValue = makeTokenSignature(expiryTime, username, password);

        setCookie(new String[] {username, Long.toString(expiryTime), signatureValue}, tokenLifetime, request, response);

        if (logger.isDebugEnabled()) {
            logger.debug("Added remember-me cookie for user '" + username + "', expiry: '"
                    + new Date(expiryTime) + "'");
        }
        
        
        setContext(null);
    }
	

    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request,
            HttpServletResponse response) {

        if (cookieTokens.length != 4) {
            throw new InvalidCookieException("Cookie token did not contain 4" +
                    " tokens, but contained '" + Arrays.asList(cookieTokens) + "'");
        }

        long tokenExpiryTime;

        try {
            tokenExpiryTime = new Long(cookieTokens[1]).longValue();
        }
        catch (NumberFormatException nfe) {
            throw new InvalidCookieException("Cookie token[1] did not contain a valid number (contained '" +
                    cookieTokens[1] + "')");
        }

        if (isTokenExpired(tokenExpiryTime)) {
            throw new InvalidCookieException("Cookie token[1] has expired (expired on '"
                    + new Date(tokenExpiryTime) + "'; current time is '" + new Date() + "')");
        }

        // Check the user exists.
        // Defer lookup until after expiry time checked, to possibly avoid expensive database call.

        UserDetails userDetails = getUserDetailsService().loadUserByUsername(cookieTokens[0]);
        //UserDetails userDetails = getVixUserDetailsService().loadUserByUsername(cookieTokens[0], cookieTokens[3]);
        
        // Check signature of token matches remaining details.
        // Must do this after user lookup, as we need the DAO-derived password.
        // If efficiency was a major issue, just add in a UserCache implementation,
        // but recall that this method is usually only called once per HttpSession - if the token is valid,
        // it will cause SecurityContextHolder population, whilst if invalid, will cause the cookie to be cancelled.
        String expectedTokenSignature = makeTokenSignature(tokenExpiryTime, userDetails.getUsername(),
                userDetails.getPassword());

        if (!equals(expectedTokenSignature,cookieTokens[2])) {
            throw new InvalidCookieException("Cookie token[2] contained signature '" + cookieTokens[2]
                                                                                                    + "' but expected '" + expectedTokenSignature + "'");
        }

        return userDetails;
    }

    /**
     * Calculates the digital signature to be put in the cookie. Default value is
     * MD5 ("username:tokenExpiryTime:password:key")
     */
    @Override
	protected String makeTokenSignature(long tokenExpiryTime, String username, String password) {
        //String data = username + ":" + tokenExpiryTime + ":" + password + ":" + getKey();
    	String data = username + ":" + tokenExpiryTime + ":" + password + ":" + getKey() + ":";
    	MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        return new String(Hex.encode(digest.digest(data.getBytes())));
    }

    @Override
    protected void setCookie(String[] tokens, int maxAge, HttpServletRequest request, HttpServletResponse response) {
    	String tenantId = request.getParameter("tenantId");
        if(StringUtils.hasLength(tenantId)){
        	 tenantId = "";
        }
         
    	String[] tokensWidthTenantId = Arrays.copyOf(tokens, tokens.length+1); 
    	tokensWidthTenantId[tokensWidthTenantId.length-1] =  tenantId;
    }

    private static boolean equals(String expected, String actual) {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        if (expectedBytes.length != actualBytes.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expectedBytes.length; i++) {
            result |= expectedBytes[i] ^ actualBytes[i];
        }
        return result == 0;
    }
    
    private static byte[] bytesUtf8(String s) {
        if (s == null) {
            return null;
        }
        return Utf8.encode(s);
    }

	@Override
	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
    
	/*public VixUserDetailsService getVixUserDetailsService() {
		return vixUserDetailsService;
	}


	public void setVixUserDetailsService(VixUserDetailsService vixUserDetailsService) {
		this.vixUserDetailsService = vixUserDetailsService;
	}
*/
	
}
