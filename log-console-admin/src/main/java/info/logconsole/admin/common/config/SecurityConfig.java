package info.logconsole.admin.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

/**
 * @author xiahongjian
 * @time 2018-06-26 21:51:18
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint())
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/login", "/css/**", "/js/**", "/fonts/**", "/druid/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login.html")
            .usernameParameter("username")
            .passwordParameter("password")
            .loginProcessingUrl("/login")
            .failureUrl("/login.html?error")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout").permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER"));
    }
}
