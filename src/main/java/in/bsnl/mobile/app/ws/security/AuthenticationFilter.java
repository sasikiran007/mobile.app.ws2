package in.bsnl.mobile.app.ws.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.bsnl.mobile.app.ws.ui.model.request.UserLoginRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    Logger logger = Logger.getLogger(AuthenticationFilter.class.getName());
    static private final String TAG = "AuthenticationFilter: ";
    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        logger.debug(TAG+"AuthenticationFilter is called");
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        logger.debug(TAG+"attemptAuthentication function is called ..post  /login");
        try {
            UserLoginRequest creds = new ObjectMapper()
                    .readValue(request.getInputStream(),UserLoginRequest.class);
            if(creds != null){
                logger.debug(TAG+"creds are "+
                        creds.getEmail()+","+ creds.getUid()+","+creds.getToken());
            }else {
                logger.debug(TAG+"creds is null");
            }
            Authentication authentication =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getUid(),
                            new ArrayList<>()
                    )
            );
            if(authentication == null) {
                logger.debug(TAG+"authentication is null");
            }
            else {
                if(authentication.isAuthenticated())
                    logger.debug(TAG+"user authentication is successful");
                else
                    logger.debug(TAG+"user authentication is failed");
            }
            return authentication;
        } catch (IOException e) {
            logger.debug(TAG+"error occurred in attemptAuthentication function ");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String userName  = ((User) authResult.getPrincipal()).getUsername();
        String tokeSecret =  SecurityConstants.getTokenSecret();

        logger.debug(TAG+"user is successfully athenticated .. building JWT toke ...");

        String token  = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256,tokeSecret)
                .compact();

        response.addHeader(SecurityConstants.HEADER_STRING,SecurityConstants.TOKEN_PREFIX+token);

        logger.debug(TAG+"token successfully added to response header");
    }
}
