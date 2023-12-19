// Implementing BCrypt password hashing:
// https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
// 1 => add spring security dependency
// 2 => create SecurityConfig.java in config package. This defines BCryptPasswordEncoder as a bean in our config.
// In Spring, beans are singletons by default(@Bean or @Component).
// 3 => We use PasswordEncoder in our UtilisateurServiceImpl (client creation and update)


package com.younes.locadrive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
