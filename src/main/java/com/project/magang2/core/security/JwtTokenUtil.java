package com.project.magang2.core.security;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.project.magang2.core.model.LoginModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.project.magang2.core.security.SecurityConstants.SECRET;
import static com.project.magang2.core.security.SecurityConstants.SECRET;
import static com.project.magang2.core.security.SecurityConstants.EXPIRATION_TIME;


@Component
public class JwtTokenUtil implements Serializable {
	public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
	
	public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
	
	public  <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
	        final Claims claims = getAllClaimsFromToken(token);
	        return claimsResolver.apply(claims);
	    }
	  
	  private Claims getAllClaimsFromToken(String token) {
	        return Jwts.parser()
	                .setSigningKey(SECRET.getBytes())
	                .parseClaimsJws(token)
	                .getBody();
	    }
	  
	  private Boolean isTokenExpired(String token) {
	        final Date expiration = getExpirationDateFromToken(token);
	        return expiration.before(new Date());
	    }
	  
	  public String generateToken(LoginModel user, Authentication auth) {
	        return doGenerateToken(user.getUsername(),auth);
	    }
	  
	  private String doGenerateToken(String subject, Authentication auth) {
		  	
		    User user1 = (User) auth.getPrincipal();
		    
	        Claims claims = Jwts.claims().setSubject(subject);
	        claims.put("roles", user1.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));

	        return Jwts.builder()
	                .setClaims(claims)
//	                .setIssuer("http://devglan.com")
//	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
	                .compact();
	    }
	  
	  public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = getUsernameFromToken(token);
	        return (
	              username.equals(userDetails.getUsername())
	                    && !isTokenExpired(token));
	    }
}
