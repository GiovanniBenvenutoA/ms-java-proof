package ms.survey.proof.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig{

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http

                .csrf((csrf) -> csrf
                        .disable())
                .authorizeRequests( authz -> authz
                        .antMatchers(HttpMethod.POST,Constans.LOGIN_URL).permitAll()
                        .antMatchers(HttpMethod.GET,Constans.SWAGGER_CONSOLE).permitAll()
                        .antMatchers(HttpMethod.POST,Constans.SWAGGER_CONSOLE).permitAll()
                        .antMatchers(HttpMethod.GET,Constans.SWAGGER_CONSOLE_V3).permitAll()
                        .anyRequest().authenticated())
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .headers().frameOptions().disable()
                .and()
                .cors();
        return http.build();
    }
}
