package com.project.magang2.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.project.magang2.core.security.SecurityConstants.HEADER_STRING;
import static com.project.magang2.core.security.SecurityConstants.SECRET;
import static com.project.magang2.core.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
//	public JWTAuthorizationFilter(AuthenticationManager authManager) {
//        super(authManager);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
        	chain.doFilter(req, res);
//            logger.warn("can't find bearer");
        }else {
        	authToken = header.replace(TOKEN_PREFIX,"");
            try {
            	username = jwtTokenUtil.getUsernameFromToken(authToken);
            }catch(IllegalArgumentException e) {
            	logger.error("error getting username from token",e);
            }catch(ExpiredJwtException e) {
            	logger.warn("token expired",e);
            }catch(SignatureException e) {
            	logger.error("Username or Password not valid");
            }
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if(jwtTokenUtil.validateToken(authToken, userDetails)) {
                	Collection<? extends GrantedAuthority> role = userDetails.getAuthorities();
                	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, role);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    System.out.println(role);
                    System.out.println(authentication);
//                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
    		chain.doFilter(req, res);
        }
    }

//    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
//        String token = request.getHeader(HEADER_STRING);
//        if (token != null) {
//            // parse the token.
//        	Claims claims = Jwts.parser()
//					.setSigningKey(SECRET.getBytes())
//					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
//					.getBody();
//
////            String user = Jwts.parser()
////                    .setSigningKey(SECRET.getBytes())
////                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
////                    .getBody()
////                    .getSubject();
//
//        	
//        	String user = claims.getSubject();
//			String role = (String) claims.get(SecurityConstants.CLAIM_ROLES);
//			
//			System.out.println(user);
////			System.out.println("isi role : "+role);
//			List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(role);
////			System.out.println("isi auth : "+authorityList);
//            if (user != null) {
////                Collection<? extends GrantedAuthority> collection = (Collection<? extends GrantedAuthority>) new ArrayList<>();
//               
////				return new UsernamePasswordAuthenticationToken(user, null, collection);
//				return new UsernamePasswordAuthenticationToken(user, null, authorityList);
//
//            }
//            return null;
//        }
//        return null;
//    }
}
