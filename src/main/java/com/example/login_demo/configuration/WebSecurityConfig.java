package com.example.login_demo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	public DataSource dataSource;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests((r) -> r.requestMatchers("/admin").hasRole("MANAGER"))
			.authorizeHttpRequests((r) -> r.anyRequest().authenticated())
			.requestCache(r -> r.disable())
			.formLogin((r) -> 
						r.usernameParameter("username")
						 .passwordParameter("password")
						 .loginPage("/login")
						 .permitAll()
						 .defaultSuccessUrl("/index"))
			.logout((r) -> r.logoutSuccessUrl("/login"));
		
		return http.build();		
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.
	        jdbcAuthentication()
	        .dataSource(dataSource)
	        .passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	@Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager mgr = new JdbcUserDetailsManager();
        mgr.setDataSource(dataSource);
        return mgr;
    }
}