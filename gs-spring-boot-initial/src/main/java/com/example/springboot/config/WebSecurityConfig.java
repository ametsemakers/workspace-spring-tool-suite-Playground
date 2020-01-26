package com.example.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ch.qos.logback.core.encoder.Encoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private UserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		//auth.inMemoryAuthentication().withUser("root").password(encoder.encode("root")).roles("USER");
		
//		auth.inMemoryAuthentication()
//			.withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN")
//			.and()
//			.withUser("user").password(encoder().encode("userPass")).roles("USER");
		
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST,"/api").authenticated().antMatchers("/").permitAll().and().httpBasic();
		
		//http.authorizeRequests().antMatchers("/").hasRole("ADMIN").anyRequest().authenticated().and().formLogin();
		
//		http
//			.csrf().disable()
//			.exceptionHandling()
//			.authenticationEntryPoint(restAuthenticationEntryPoint)
//			.and()
//			.authorizeRequests()
//			.antMatchers("/api/foos").authenticated()
//			.antMatchers("/api/admin/**").hasRole("ADMIN")
//			.and()
//			.formLogin()
//			.successHandler(mySuccessHandler)
//			.failureHandler(myFailureHandler)
//			.and()
//			.logout();
		
		http.csrf().disable()
			.authorizeRequests().antMatchers("/authenticate").permitAll()
			.anyRequest().authenticated().and()
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// valider le jéton à chaque reqûete
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
}
