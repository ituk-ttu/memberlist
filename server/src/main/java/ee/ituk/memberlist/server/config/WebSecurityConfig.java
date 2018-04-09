package ee.ituk.memberlist.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.ituk.memberlist.server.security.SkipPathRequestMatcher;
import ee.ituk.memberlist.server.security.jwt.JwtAuthenticationProcessingFilter;
import ee.ituk.memberlist.server.security.jwt.JwtAuthenticationProvider;
import ee.ituk.memberlist.server.security.jwt.TokenExtractor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String AUTHENTICATION_URL = "/auth";
    public static final String ADDITIONAL_AUTH_URL_PATTERN = "/auth/**";
    public static final String API_ROOT_URL = "/**";

    @Resource
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Resource
    private TokenExtractor tokenExtractor;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Resource
    private ObjectMapper objectMapper;

    protected JwtAuthenticationProcessingFilter buildJwtAuthenticationProcessingFilter(List<String> pathsToSkip, String pattern) throws Exception {
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, pattern);
        JwtAuthenticationProcessingFilter filter
                = new JwtAuthenticationProcessingFilter(authenticationFailureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] permitAllEndpointList = new String[]{
                AUTHENTICATION_URL,
                ADDITIONAL_AUTH_URL_PATTERN
        };

        http
                .csrf().disable()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(permitAllEndpointList)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(API_ROOT_URL).authenticated() // Protected API End-points
                .and()
                .addFilterBefore(buildJwtAuthenticationProcessingFilter(Arrays.asList(permitAllEndpointList),
                        API_ROOT_URL), UsernamePasswordAuthenticationFilter.class);
    }
}