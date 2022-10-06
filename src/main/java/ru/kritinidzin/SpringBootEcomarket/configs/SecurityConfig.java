package ru.kritinidzin.SpringBootEcomarket.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    private static final String USER_SQL = "SELECT" +
            " login," +
            " password," +
            " true" +
            " FROM" +
            " user" +
            " WHERE" +
            " login = ?";

    private static final String ROLE_SQL = "SELECT" +
            " login," +
            " role" +
            " FROM" +
            " user" +
            " WHERE" +
            " login = ?";

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/loginUp").permitAll()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/shop/**").hasRole("USER")
                .antMatchers("/home/**").hasRole("USER")
                .antMatchers("/shopAdd/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.formLogin()
                .loginProcessingUrl("/loginUp")
                .loginPage("/loginUp")
                .failureUrl("/loginUp")
                .usernameParameter("login")
                .passwordParameter("password")
                .defaultSuccessUrl("/shopAll", true);

        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_SQL)
                .authoritiesByUsernameQuery(ROLE_SQL)
                .passwordEncoder(passwordEncoder());
    }
}
