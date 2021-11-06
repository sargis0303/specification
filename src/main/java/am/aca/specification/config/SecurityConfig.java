package am.aca.specification.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final CurrentUserService currentUserService;
//    private final PasswordEncoder passwordEncoder;
//    private final TokenService tokenService;
//
//    public SecurityConfig(CurrentUserService currentUserService, PasswordEncoder passwordEncoder, TokenService tokenService) {
//        this.currentUserService = currentUserService;
//        this.passwordEncoder = passwordEncoder;
//        this.tokenService = tokenService;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().anyRequest().permitAll();
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(new FilterExceptionHandler(), AuthenticationFilter.class)
//                .addFilter(new AuthenticationFilter(authenticationManager(), tokenService));
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(currentUserService)
//                .passwordEncoder(passwordEncoder);
//    }

}
