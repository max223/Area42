package com.rogiss.project.area42.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

//@formatter:off
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private UserDetailsService userDetailsService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/css/**", "/connect/**").permitAll()
                    .antMatchers("/dashboard/**").authenticated()
                    .antMatchers("/area/**").authenticated()
                    .antMatchers("/secure/**")
                    .authenticated()
                    .antMatchers("/webhook/**").permitAll()
                    .and()
                    .csrf().ignoringAntMatchers("/webhook/**")
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/secure/user")
                    .failureUrl("/login-error")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();
        }

            @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        }

    }

// @formatter:on

