package com.cybersoft.cozastore_java21.config;

import com.cybersoft.cozastore_java21.filter.JwtFilter;
import com.cybersoft.cozastore_java21.provider.CustomAuthenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomAuthenProvider authenticationProvider;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider)
                .build();
    }

    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf().disable() // Tat cau hinh lien quan den tan cong CSRF
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Khai bao khong su dung session trong project
                .and()
                .authorizeHttpRequests()
                    .antMatchers("/signin","/signup","/demo/**").permitAll()
                    .antMatchers("/admin").hasRole("ADMIN") // hasRole : phai co quyen moi vao duoc
                    .antMatchers("/admin/save").hasAnyRole("ADMIN", "SAVE")
                    .antMatchers("/admin/delete").hasRole("DELETE")
                    .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                    .anyRequest().authenticated() // Tat ca cac link con lai deu phai co quyen moi vao duoc
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
