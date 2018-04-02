package ee.ituk.memberlist.server.config;

import ee.ituk.memberlist.server.auth.jwt.JwtClaimsHandlerMethodArgumentResolver;
import ee.ituk.memberlist.server.auth.jwt.JwtService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.List;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Resource
    private JwtService jwtService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JwtClaimsHandlerMethodArgumentResolver(jwtService));
        argumentResolvers.add(new WebClientUrlHandlerMethodArgumentResolver());
    }

}
