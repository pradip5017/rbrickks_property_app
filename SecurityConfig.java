package security;


import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

//package com.rbrickks.property.security;

//import org.springframework.beans.factory.annotation.Autowired; 
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
	
//	 @Autowired
//	    private JwtFilter jwtFilter;
//
//	    @Bean
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	        http.csrf().disable()
//	            .authorizeHttpRequests()
//	            .requestMatchers("/auth/**", "/properties/public/**").permitAll()
//	            .anyRequest().authenticated()
//	            .and()
//	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//	        return http.build();
//	    }
//
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//
//	    @Bean
//	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//	        return configuration.getAuthenticationManager();
//	    }
	

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors().and()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/properties/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(); // Or use JWT-based authentication if needed

        return http.build();
    }
    
    // chenges pending
    @Bean
    public  FilterRegistrationBean coresFilter() {
    	UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
    	
    	CorsConfiguration corsConfiguration = new CorsConfiguration();
    	corsConfiguration.setAllowCredentials(true);
    	corsConfiguration.addAllowedOriginPattern("*");
    	corsConfiguration.addAllowedHeader("Authorization");
    	corsConfiguration.addAllowedHeader("Content-Type");
    	corsConfiguration.addAllowedHeader("Accept");
    	corsConfiguration.addAllowedMethod("POST");
    	corsConfiguration.addAllowedMethod("GET");
    	corsConfiguration.addAllowedMethod("DELETE");
    	corsConfiguration.addAllowedMethod("PUT");
    	corsConfiguration.addAllowedMethod("OPTIONS");
    	corsConfiguration.setMaxAge(3600l);
    	
    	
    	
    	
    	source.registerCorsConfiguration("/**", corsConfiguration);
    	FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter());
    	return bean;
    }
    
}
