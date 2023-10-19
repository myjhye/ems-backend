package net.javaguides.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> {
                    authorize.regexMatchers(HttpMethod.POST, "/api/.*").hasRole("ADMIN");
                    authorize.regexMatchers(HttpMethod.PUT, "/api/.*").hasRole("ADMIN");
                    authorize.regexMatchers(HttpMethod.DELETE, "/api/.*").hasRole("ADMIN");
                    authorize.regexMatchers(HttpMethod.GET, "/api/.*").hasAnyRole("ADMIN", "USER");
                    authorize.regexMatchers(HttpMethod.PATCH, "/api/.*").hasAnyRole("ADMIN", "USER");

                    authorize.anyRequest().hasRole("ADMIN");
                })
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails myjhye = User.builder()
                .username("myjhye")
                .password(passwordEncoder().encode("1111"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(myjhye, admin);

        // Debug print to check roles assigned to users
        userDetailsManager.createUser(User.builder()
                .username("debugUser")
                .password(passwordEncoder().encode("password"))
                .roles("USER", "ADMIN")
                .build());

        return userDetailsManager;
    }
}
