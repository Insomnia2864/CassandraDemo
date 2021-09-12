package com.example.cassandrademo.jwt;

import com.example.cassandrademo.properties.JwtApplicationProperties;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtTokenVerifierFilter extends OncePerRequestFilter {

    JwtApplicationProperties properties = new JwtApplicationProperties();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("Authorization");
        if (Strings.isNullOrEmpty(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(properties.getSecretKey().getBytes()))
                    .build()
                    .parseClaimsJws(token);

            Claims body = claims.getBody();
            String username = body.getSubject();
            List<Map<String, String>> authorities =  (List<Map<String, String>>)body.get("authorities");
            Set<SimpleGrantedAuthority> mappedAuthorities =  authorities.stream()
                    .map(map -> new SimpleGrantedAuthority(map.get("authority")))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    mappedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (Exception exception) {
            log.warn("Error occurred", exception);
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }
        finally {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
