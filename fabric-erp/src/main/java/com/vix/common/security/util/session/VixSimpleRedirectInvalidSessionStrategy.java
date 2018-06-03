package com.vix.common.security.util.session;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import com.vix.common.security.util.SecurityUtil;

/**
 * Performs a redirect to a fixed URL when an invalid requested session is detected by the {@code SessionManagementFilter}.
 *
 * @author Luke Taylor
 */
public final class VixSimpleRedirectInvalidSessionStrategy{// implements InvalidSessionStrategy 
    private final Log logger = LogFactory.getLog(getClass());
    private final String destinationUrl;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private boolean createNewSession = true;

    public VixSimpleRedirectInvalidSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
    }

    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug("Starting new session (if required) and redirecting to '" + destinationUrl + "'");
        if (createNewSession) {
            request.getSession();
        }
        
        String tenantId = request.getParameter("tenantId");
		String j_username = request.getParameter("j_username");
		
		//增加tenantId
		String tenantIdDestinationUrl = SecurityUtil.addParamTenantId(j_username,destinationUrl, tenantId);
		
        redirectStrategy.sendRedirect(request, response, tenantIdDestinationUrl);
    }

    /**
     * Determines whether a new session should be created before redirecting (to avoid possible looping issues where
     * the same session ID is sent with the redirected request). Alternatively, ensure that the configured URL
     * does not pass through the {@code SessionManagementFilter}.
     *
     * @param createNewSession defaults to {@code true}.
     */
    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }
}
