package uz.devs.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static uz.devs.springsecurity.security.UserRole.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user_alitalib = User.builder()
                .username("alitalib")
                .password(passwordEncoder.encode("number1man"))
                .roles(STUDENT.name())     //ROLE_STUDENT
                .build();

        UserDetails user_abubakr = User.builder()
                .username("abubakr")
                .password(passwordEncoder.encode("number1teacher"))
                .roles(ADMIN.name())
                .build();

        UserDetails user_abdallah = User.builder()
                .username("abdullah")
                .password(passwordEncoder.encode("number2man"))
                .roles(ADMINTRAINEE.name())
                .build();

        return new InMemoryUserDetailsManager(
                user_alitalib,
                user_abubakr,
                user_abdallah
        );
    }
}
