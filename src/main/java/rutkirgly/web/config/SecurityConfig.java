package rutkirgly.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(AuthenticationProvider authenticationProvider){
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/admin/updateUserRole").hasRole("ADMIN")
                        .requestMatchers("/auth/registration","/brand/", "/", "/model/", "/home/").permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("admin/user-list").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin()
                    .loginPage("/auth/login")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/auth/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll();
        return http.build();
    }

}
