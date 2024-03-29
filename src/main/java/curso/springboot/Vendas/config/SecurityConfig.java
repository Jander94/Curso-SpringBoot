package curso.springboot.Vendas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .httpBasic()
            .and()
            .authorizeHttpRequests((authz) -> authz
                    .requestMatchers(HttpMethod.POST,"/api/user").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/api/roles").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .csrf().disable();
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
