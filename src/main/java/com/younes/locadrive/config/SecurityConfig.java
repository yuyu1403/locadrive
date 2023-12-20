// Implementing BCrypt password hashing:
// https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
// 1 => add spring security dependency
// 2 => create SecurityConfig.java in config package. This defines BCryptPasswordEncoder as a bean in our config.
// In Spring, beans are singletons by default(@Bean or @Component).
// 3 => We use PasswordEncoder in our UtilisateurServiceImpl (client creation and update)


package com.younes.locadrive.config;

import com.younes.locadrive.model.Utilisateur;
import com.younes.locadrive.model.enums.UserRole;
import com.younes.locadrive.repos.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import java.util.Collection;
import java.util.Collections;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UtilisateurRepository utilisateurRepository;


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Utilisateur utilisateur = utilisateurRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

            // Convert the Utilisateur entity to UserDetails
            return User.builder()
                    .username(utilisateur.getEmail()) // or .username(username)
                    .password(utilisateur.getPassword())
                    .authorities(getAuthorities(utilisateur.getRole()))
                    .accountExpired(false)  // set according to your needs
                    .accountLocked(false)   // set according to your needs
                    .credentialsExpired(false) // set according to your needs
                    .disabled(false)        // set according to your needs
                    .build();
        };
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserRole role) {
        // Convert the UserRole to GrantedAuthority
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


}
