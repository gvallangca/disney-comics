package com.disney.demo.security.jwt;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Strings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

/**
 * This is executed only once per request
 * 
 * @author gem_v
 *
 */
@AllArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {
	
	private final SecretKey secretKey;
	private final JwtConfig jwtConfig;
	
	/**
	 * Validates the sent JWT
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorizationHeader = request.getHeader(jwtConfig.getAuthotizationHeader());

		if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {

			filterChain.doFilter(request, response);
			return;

		}

		String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
		
		Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build()
				.parseClaimsJws(token);

		/* This is the main body of the JWT token
			{
			  "sub": "linda",
			  "authorities": [
			    {
			      "authority": "student:write"
			    },
			    {
			      "authority": "student:read"
			    },
			    {
			      "authority": "course:read"
			    },
			    {
			      "authority": "ROLE_ADMIN"
			    },
			    {
			      "authority": "course:write"
			    }
			  ],
			  "iat": 1587342530,
			  "exp": 1588489200
			}
		*/
		
		Claims body = claimsJws.getBody();
		
		/* "sub": "linda" */
		String username = body.getSubject();
		
		List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

		Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
				.map(map -> new SimpleGrantedAuthority(map.get("authority"))).collect(Collectors.toSet());

		Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
				simpleGrantedAuthorities);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		filterChain.doFilter(request, response);

	}

}