package com.example.app_for_task.config;


import com.example.app_for_task.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;
    private final UserDetailsServiceImpl userDetailsService;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/", "/user/login").permitAll()
                .anyRequest().authenticated()
                //.requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
                //.requestMatchers().permitAll()
                //.requestMatchers("/user/login").permitAll()
                .and()
                .formLogin()
                .successHandler(successUserHandler)
                .permitAll()
                //.loginPage("/admin/login")
                //.usernameParameter("firstName")
                //.successHandler(successUserHandler)
//                .defaultSuccessUrl("/admin/home")
//                .and()
//                .logout().logoutUrl("/admin/logout")
//                .logoutSuccessUrl("/");
                .and()
                .logout()
                .permitAll();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }

    @Bean
    public static PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

//    @Bean
//    protected DaoAuthenticationProvider daoAuthenticationProvider() {
//    DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//    dao.setPasswordEncoder(passwordEncoder());
//    dao.setUserDetailsService(userDetailsService);
//    return dao;
//    }
}
