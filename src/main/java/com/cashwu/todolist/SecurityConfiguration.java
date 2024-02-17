package com.cashwu.todolist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.core.userdetails.User.*;

/**
 * @author cash
 */
@Configuration
public class SecurityConfiguration {

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((auth) ->
                        auth.anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
//                .authenticationManager(new CustomAuthManager());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/");
    }

    @Bean
    InMemoryUserDetailsManager userDetailsService() {

        var builder = User.builder();

        UserDetails userDetail = builder.username("cash")
//                .password("cash-1234")
                .password("$2a$10$oC3EpFwR5CepJUjA95zi8.3MKKiPQ3ns8EKKhOjYape01J4GJTmqm")
                .roles("USER")
                .build();

//        UserDetails userDetail = User.withDefaultPasswordEncoder()
//                .username("cash")
//                .password("cash-1234")
//                .roles("USER")
//                .build();

        return new InMemoryUserDetailsManager(userDetail);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

