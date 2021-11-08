package com.example.springdata.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig {

    //создадим какого-то дефолтного юзера, чтобы через него всегда можно было зайти, не используя
    //пароль, который генерит спринг
    @Autowired
    public void authConfigure(AuthenticationManagerBuilder auth,
                              UserAuthService userAuthService,
                              PasswordEncoder encoder) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("main_user")
                .password(encoder.encode("password"))
                .roles("ADMIN");

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userAuthService);
        provider.setPasswordEncoder(encoder);
        auth.authenticationProvider(provider);
    }

    @Configuration
    public static class UiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/user/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin();
        }
    }
}
