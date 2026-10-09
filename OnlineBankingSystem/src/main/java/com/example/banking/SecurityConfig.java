package com.example.banking;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService userDetailsService(UserRepository repo){return username->repo.findByUsername(username).map(u->User.withUsername(u.username).password(u.password).roles("USER").build()).orElseThrow(()->new UsernameNotFoundException("User not found"));}
 @Bean SecurityFilterChain filterChain(HttpSecurity http)throws Exception{return http.authorizeHttpRequests(a->a.requestMatchers("/login","/css/**","/error").permitAll().anyRequest().authenticated()).formLogin(f->f.loginPage("/login").defaultSuccessUrl("/dashboard",true).permitAll()).logout(l->l.logoutSuccessUrl("/login?logout")).build();}
}
