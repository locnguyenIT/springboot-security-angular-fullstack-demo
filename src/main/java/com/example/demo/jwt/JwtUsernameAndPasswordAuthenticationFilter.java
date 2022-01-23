package com.example.demo.jwt;

import com.example.demo.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //AuthenticationManager check username exist and password is correct then user will be authenticated
    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //Authentication for user
        try{
            //(/login || POST) use ObjectMapper to read value from InputStream and get field of UsernameAndPasswordAuthenticationRequest.class to perform JSON  data
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper().readValue(request.getInputStream(),
                                                                                                          UsernameAndPasswordAuthenticationRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                                                                                    authenticationRequest.getPassword());

            //Use AuthenticationManager to authenticate username & password
            Authentication authenticate = authenticationManager.authenticate(authentication);

            //System.out.println("Account successfully authenticated");
            return authenticate;

        }catch (IOException e)
        {
            throw new IllegalStateException("Failed to authenticate user");
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> authFailed = new HashMap<>();
        authFailed.put("message",failed.getMessage());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        new ObjectMapper().writeValue(response.getOutputStream(),authFailed);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        //Create token
        String secretKey = "keykeykeykeykeykeykeykeykeykeykeykey";
        String token = Jwts.builder()
                .setSubject(authResult.getName()) //subject (username)
                .claim("authorities", authResult.getAuthorities()) //Authorities
                .setIssuedAt(new Date()) //iat
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(2))) //exp: token expired 2 day
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes())) //verify signature
                .compact();

        Map<String, String> jwt_user = new HashMap<>();
        jwt_user.put("username",authResult.getName());
        jwt_user.put("token","Bearer "+token);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(),jwt_user);

//        //response token to client
//        response.addHeader("Authorization","Bearer "+token);

        System.out.println("Token: "+token);
    }


}
