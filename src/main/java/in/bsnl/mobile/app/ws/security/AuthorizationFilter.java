package in.bsnl.mobile.app.ws.security;

import in.bsnl.mobile.app.ws.exceptions.UserServiceException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    Logger logger = Logger.getLogger(AuthorizationFilter.class.getName());

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        logger.debug("AuthorizationFilter called");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        logger.debug("doFilterInternal function was called");
        String header = request.getHeader(SecurityConstants.HEADER_STRING);

        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            logger.debug("AuthorizationFilter: header is null .. chain to next filter");
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = null;
        authenticationToken = getAuthenticationToken(request, response);
        if (authenticationToken != null) {
            logger.debug("AuthorizationFilter: authenticationToken is " + authenticationToken);

        } else {
            logger.debug("AuthorizationFilter: authenticationToken is null");
            throw new UserServiceException("null ");
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request,
                                                               HttpServletResponse response)  {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);

        if (token != null) {
            logger.debug("AuthorizationFilter:  token is " + token);
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");

            try {
                String user = Jwts.parser()
                        .setSigningKey(SecurityConstants.getTokenSecret())
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();

                if (user != null) {
                    logger.debug("AuthorizationFilter: user is" + user);
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());

                } else {
                    logger.debug("AuthorizationFilter: user is null");
                }

            } catch (ExpiredJwtException e) {
                final String expiredMsg = e.getMessage();
                logger.debug(expiredMsg);

//                final String msg = (expiredMsg != null) ? expiredMsg : "Unauthorized";
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg);
                System.out.println(" Token expired ");
//                return null;
            } catch (SignatureException e) {
                Logger.getLogger(AuthorizationFilter.class.getName()).debug(e);
                return null;
            } catch (Exception e) {
                System.out.println(" Some other exception in JWT parsing ");
                return null;
            }

            return null;
        } else {
            logger.debug("AuthorizationFilter:  token is  null");
        }
        return null;

    }

}
