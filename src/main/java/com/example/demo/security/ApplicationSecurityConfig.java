package com.example.demo.security;

import com.example.demo.authentication.AppUserDetailsService;
import com.example.demo.jwt.JwtTokenVerifier;
import com.example.demo.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.example.demo.role.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.example.demo.role.EnumRole.*;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AppUserDetailsService appUserDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ApplicationSecurityConfig(AppUserDetailsService appUserDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserDetailsService = appUserDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .cors()
                .and()
                .csrf().disable() //Disable CSRF protection, because I'm using Postman to test API
                //spring security not created or used session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //Authentication(username & password) from client & send token to client
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                //Verifier Token from client after authenticated user was successfully and client make some request to api
                .addFilterAfter(new JwtTokenVerifier(),JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/","/favicon.ico","/*.js","/*.css","/registration/**","/forgot-password/**","/api/student/show/**").permitAll()

//                .antMatchers(HttpMethod.GET,"/api/student/**").hasAnyAuthority(USER.name(),ADMIN.name())
//                .antMatchers(HttpMethod.GET,"/api/user/**").hasAnyAuthority(USER.name(),ADMIN.name())
//                .antMatchers(HttpMethod.POST,"/api/student/add/**").hasAuthority(ADMIN.name())
//                .antMatchers(HttpMethod.DELETE,"/api/student/delete/**").hasAuthority(ADMIN.name())
//                .antMatchers(HttpMethod.PUT,"/api/student/update/**").hasAuthority(ADMIN.name())
                .anyRequest()
                .authenticated();


    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    //Spring will look for the user try login to application
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserDetailsService);
        return provider;
    }
}
