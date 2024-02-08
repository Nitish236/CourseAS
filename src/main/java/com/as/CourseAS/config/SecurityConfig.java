package com.as.CourseAS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("student").password(passwordEncoder().encode("student")).roles("STUDENT").build(),
                User.withUsername("teacher").password(passwordEncoder().encode("teacher")).roles("TEACHER").build(),
                User.withUsername("coursecreator").password(passwordEncoder().encode("coursecreator")).roles("COURSE_CREATOR").build()
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.httpBasic(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .requestMatchers("/courses/create", "/course/delete").hasRole("COURSE_CREATOR")
                .requestMatchers("/courses/**").hasAnyRole("STUDENT", "COURSE_CREATOR", "TEACHER")
                .anyRequest().authenticated()
            ).formLogin(Customizer.withDefaults()).build();
    }
}
