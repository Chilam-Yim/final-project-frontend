package FinalProject_frontend.config;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import FinalProject_frontend.model.UserDetail;
import FinalProject_frontend.repository.UserRepository;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            UserDetail user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException(username);
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                    .requestMatchers(toH2Console()).permitAll()
                    .requestMatchers("/", "/login", "/register").permitAll()
                    .requestMatchers("/home").hasAnyRole("USER","ADMIN","EMPLOYEE")
                    .requestMatchers("/add").hasAnyRole("ADMIN","EMPLOYEE")
                    .requestMatchers("/items").hasAnyRole("USER","ADMIN","EMPLOYEE")
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login") // Set /login as the login page
                    .defaultSuccessUrl("/home", true)
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                .and()
                    .csrf()
                    .ignoringRequestMatchers(toH2Console())
                .and()
                    .headers()
                    .frameOptions().sameOrigin();
        return http.build();
    }


}
