package nju.se4.demo.security;

import nju.se4.demo.data.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * spring security的配置
 *
 * @author xuxiangzhe
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserDAO userDAO;

    @Value("${api_url.loginUrl}")
    private String loginUrl;

    @Value("${api_url.registerUrl}")
    private String registerUrl;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, UserDAO userDAO) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.userDAO = userDAO;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        JwtLoginFilter loginFilter = new JwtLoginFilter(authenticationManager());
        loginFilter.setUserDAO(userDAO);
        loginFilter.setAuthenticationFailureHandler(new AuthFailHandler());
        loginFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(loginUrl, "POST"));
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager(), userDetailsServiceImpl, new RestAuthorEntry());
        http
                .authorizeRequests()
                .antMatchers(registerUrl).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(loginFilter)
                .addFilter(jwtAuthenticationFilter)
                .cors()
                .and()
                .csrf()
                .disable();

    }

    /**
     * use customized  user detail service
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl);
    }
}

