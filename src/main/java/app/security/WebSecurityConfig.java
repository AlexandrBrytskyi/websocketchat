package app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

   /* @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/admin*/

    /**
     * ")
     * .access("hasRole('ROLE_ADMIN')").and().formLogin()
     * .loginPage("/login").failureUrl("/login?error")
     * .usernameParameter("username")
     * .passwordParameter("password")
     * .and().logout().logoutSuccessUrl("/login?logout")
     * .and().csrf()
     * .and().exceptionHandling().accessDeniedPage("/403");
     * }
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().sessionManagement().sessionFixation().migrateSession().and()
                .authorizeRequests()
                .antMatchers("/chat/chat").access("hasRole('USER')")
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/chat/login")
                .failureForwardUrl("/chat/login?credentials=false")
                .usernameParameter("username")
                .passwordParameter("password")
                .successForwardUrl("/chat/logined")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

}