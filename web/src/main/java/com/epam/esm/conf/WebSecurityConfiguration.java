package com.epam.esm.conf;

import com.epam.esm.security.AuthEntryPointHandler;
import com.epam.esm.security.CustomAccessDeniedHandler;
import com.epam.esm.security.JwtConfigurer;
import com.epam.esm.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;


/**
 * Web security configuration.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final static String MAIN_CERTIFICATES = "/certificates";
    private final static String AUTH_ENDPOINT = "/auth/**";
    private AuthEntryPointHandler authEntryPointHandler;
    private JwtTokenProvider jwtTokenProvider;

    /**
     * Sets auth entry point handler.
     *
     * @param authEntryPointHandler the auth entry point handler
     */
    @Autowired
    public void setAuthEntryPointHandler(AuthEntryPointHandler authEntryPointHandler) {
        this.authEntryPointHandler = authEntryPointHandler;
    }

    /**
     * Sets jwt token provider.
     *
     * @param jwtTokenProvider the jwt token provider
     */
    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_ENDPOINT, MAIN_CERTIFICATES).permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider))
                .and()
                .httpBasic()
                .authenticationEntryPoint(authEntryPointHandler);

    }

    /**
     * Access denied handler access denied handler.
     *
     * @return the access denied handler
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    /**
     * Password encoder b crypt password encoder.
     *
     * @return the b crypt password encoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
