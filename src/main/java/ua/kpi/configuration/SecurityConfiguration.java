package ua.kpi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String MATCH_ALL = "/**";

    @Value("${spring.security.oauth2.client.registration.github.client-name}")
    private String githubUserRole;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(MATCH_ALL).hasRole(githubUserRole)
                .anyRequest().authenticated()
                .and()
                .cors().and()
                .oauth2Login();
    }
}
