package example.cashcard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// Add this Annotation to turn this clas as configuration for Spring Security
@Configuration
class SecurityConfig {

    // Add this Annotation to turn this clas as configuration for Spring Security
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // All HTTP requests to cashcards/ endpoints are required to be authenticated
        // using HTTP Basic Authentication security (username and password).
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/cashcards/**")
                        .hasRole("CARD-OWNER"))
                        //in case you want to authenticate using credentials
                        //.authenticated())
                .httpBasic(Customizer.withDefaults())
                // Also, do not require CSRF security.
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
    //This UserDetailsService configuration configures a user named sarah1 with password abc123
    @Bean
    UserDetailsService testOnlyUsers(PasswordEncoder passwordEncoder) {
     User.UserBuilder users = User.builder();
     UserDetails sarah = users
       .username("sarah1")
       .password(passwordEncoder.encode("abc123"))
       .roles("CARD-OWNER") // No roles for now
       .build();
       UserDetails hankOwnsNoCards = users
       .username("hank-owns-no-cards")
       .password(passwordEncoder.encode("qrs456"))
       .roles("NON-OWNER") // No roles for now
       .build();
     return new InMemoryUserDetailsManager(sarah,hankOwnsNoCards);
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
