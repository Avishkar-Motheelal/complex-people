package com.example.complexpeople.config;

import com.example.complexpeople.security.jwt.JwtAuthenticationEntryPoint;
import com.example.complexpeople.security.jwt.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;
    private final UserDetailsService userDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
//            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers("/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/docs/**",
                "/swagger",
                "/docs",
                "/users/**",
//                "/**",
                "/login/oauth2/code/google").permitAll()
//            .requestMatchers("/**").hasRole("STAFF")
//            .requestMatchers("/access/authorization").hasAnyRole("ADMIN", "READER")
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User
//            .withUsername("admin")
//            .password(passwordEncoder().encode("password"))
//            .roles("ADMIN")
//            .build();
//
//        UserDetails cardReader = User
//            .withUsername("cardReader")
//            .password(passwordEncoder().encode("password"))
//            .roles("READER")
//            .build();
//        return new InMemoryUserDetailsManager(user, cardReader);
//    }
}
