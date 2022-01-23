package com.example.demo.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter { //invoke this filter for every single request come from client
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization"); //get token from client

        if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer "))
        {
            //if token null or empty or token not start with Bearer. The request from client will be rejected
            filterChain.doFilter(request,response);
            return;
        }
        String token = authorizationHeader.replace("Bearer ","");
        try
        {
            String secretKey = "keykeykeykeykeykeykeykeykeykeykeykey";
            //A signed JWT is called a 'JWS' || use secretKey to Decode JWT
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())).build()
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody(); //payload

            String username = body.getSubject(); //username

            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

            List<SimpleGrantedAuthority> simpleGrantedAuthorities= authorities.stream().map(
                    m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toList()); //authorities

            Authentication authentication = new UsernamePasswordAuthenticationToken(username,
                                                                            null,
                                                                                     simpleGrantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authentication); //JWT is valid will authentication for user
        }catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s can't be truest", token));
        }
        //token is correct then allow client request access to api
        filterChain.doFilter(request, response);

    }
}
