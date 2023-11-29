package com.tp.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
        /*premier ligne il autorise les requete HTTP*/
        http.authorizeHttpRequests((auth) -> auth
                /*deuxième ligne on capture la request et on permet a tlm de voir cette url  .permitall*/
                .requestMatchers("/clients").hasRole("USER")
                .requestMatchers("/clients/id").hasRole("ADMIN")
                .requestMatchers("/orders").hasRole( "USER")
                .requestMatchers("/orders/id").hasRole("ADMIN")
                /*le reste a besoin d'une autorisation */
                .anyRequest().authenticated()
        ).httpBasic();

        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user1"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin2"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user,admin);
   }
@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
}

}
