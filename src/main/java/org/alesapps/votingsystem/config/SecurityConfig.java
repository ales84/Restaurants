package org.alesapps.votingsystem.config;

import org.alesapps.votingsystem.util.PasswordUtil;
import org.alesapps.votingsystem.web.CustomAccessDeniedHandler;
import org.alesapps.votingsystem.web.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * Created by Anatoliy Kozhayev on 14.06.2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .antMatcher("/api/v1/**")
                .authorizeRequests()
                .antMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .antMatchers("/api/v1/profile").authenticated()
                .antMatchers("/api/v1/restaurants").authenticated()
                .antMatchers("/api/v1/menus").authenticated()
                .and().httpBasic()
                .authenticationEntryPoint(getAuthenticationEntryPoint())
                .and().exceptionHandling()
                .accessDeniedHandler(getAccessDeniedHandler())

                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return PasswordUtil.getPasswordEncoder();
    }

    @Bean
    public CustomAccessDeniedHandler getAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public CustomAuthenticationEntryPoint getAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }
}
