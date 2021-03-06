package com.kodilla.projectbackend.security;

import com.kodilla.projectbackend.service.AppUserDbService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AppUserDbService appUserDbService;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDbService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.httpBasic().and().authorizeRequests()
               .antMatchers(HttpMethod.GET,"/v1/appUsers/admin/users").hasAuthority("ROLE_ADMIN")
               .antMatchers(HttpMethod.GET, "/v1/appUsers/admin/users/").hasAuthority("ROLE_ADMIN")
               .antMatchers(HttpMethod.GET, "/v1/appInfoUsers/admin/users").hasAuthority("ROLE_ADMIN")
               .antMatchers(HttpMethod.GET, "/v1/appInfoUsers/admin/users/").hasAuthority("ROLE_ADMIN")
               .antMatchers(HttpMethod.GET,"/v1/appUserMessages/admin/allMessage").hasAuthority("ROLE_ADMIN")
               .antMatchers(HttpMethod.GET,"/v1/appUserMessages/admin/allMessage/").hasAuthority("ROLE_ADMIN")
               .antMatchers(HttpMethod.GET, "/v1/appProblem/admin").hasAuthority("ROLE_ADMIN")
               .antMatchers(HttpMethod.GET, "/v1/appProblem/admin/").hasAuthority("ROLE_ADMIN")
               .antMatchers(HttpMethod.DELETE, "/v1/appUsers/admin/delete").hasAuthority("ROLE_ADMIN")
               .antMatchers(HttpMethod.DELETE,"/v1/appUserMessages/admin/delete").hasAuthority("ROLE_ADMIN")
               .and()
               .formLogin().permitAll()
               .and()
               .headers().disable()
               .csrf().disable();
    }

}
