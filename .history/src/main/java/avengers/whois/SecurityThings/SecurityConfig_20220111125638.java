package avengers.whois.SecurityThings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/chat_history/**", "/imgs/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login", "/member/join_worker", "/trylogin", "/member/join_corporate",
                        "/member/signup",
                        "/member/emailCheck")
                .permitAll()
                .antMatchers("/member/info", "/loadAdditional", "/loadBasic", "/logout").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/")
                .usernameParameter("email")
                .defaultSuccessUrl("/member/info")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}