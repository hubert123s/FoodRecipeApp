package com.example.foodrecipeapp;



//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class FoodRecipeSecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("hubert@exampl.org")
//                .password("{noop}123")
//                .roles("USER")
//                .and()
//                .withUser("ADMIN")
//                .password("{noop}123")
//                .roles("ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                .mvcMatchers(HttpMethod.GET,"/meal/**").permitAll()
//                .mvcMatchers(HttpMethod.POST,"/meal/**").permitAll()
//                .mvcMatchers(HttpMethod.POST,"/subscriber/**").permitAll()
//                .mvcMatchers("/swagger-ui/**","/v3/api-docs").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .csrf().disable();
//    }
//}
