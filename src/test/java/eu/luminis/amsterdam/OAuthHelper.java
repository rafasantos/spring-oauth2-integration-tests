package eu.luminis.amsterdam;

import eu.luminis.amsterdam.config.AuthorizationServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.util.Collections;
import java.util.Set;

@Component
public class OAuthHelper {

    @Autowired
    AuthorizationServerTokenServices tokenservice;

    public RequestPostProcessor addBearerToken(final String username, String... authorities) {
        return mockRequest -> {
            Set<String> scopes = Collections.singleton("SCOPE_USER");
            // Create OAuth2 token
            OAuth2Request oauth2Request = new OAuth2Request(null, AuthorizationServerConfig.CLIENT_ID, null, true, scopes, null, null, null, null);
            Authentication userauth = new TestingAuthenticationToken(username, null, authorities);
            OAuth2Authentication oauth2auth = new OAuth2Authentication(oauth2Request, userauth);
            OAuth2AccessToken token = tokenservice.createAccessToken(oauth2auth);

            // Set Authorization header to use Bearer
            mockRequest.addHeader("Authorization", "Bearer " + token.getValue());
            return mockRequest;
        };
    }
}
