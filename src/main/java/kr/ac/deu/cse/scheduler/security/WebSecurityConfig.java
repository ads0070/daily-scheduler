package kr.ac.deu.cse.scheduler.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtTokenProvider jwtTokenProvider;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

  @Autowired
  public WebSecurityConfig(
    JwtTokenProvider jwtTokenProvider,
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
    JwtAccessDeniedHandler jwtAccessDeniedHandler
  ) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().
      antMatchers(
        "/",
        "/h2-console/**",
        "/api/explorer/index.html"
      );
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().httpBasic().disable();
    http.headers().frameOptions().sameOrigin();

    http.sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http
      .exceptionHandling()
      .authenticationEntryPoint(jwtAuthenticationEntryPoint)
      .accessDeniedHandler(jwtAccessDeniedHandler);

    http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

    http
      .authorizeRequests()
      .antMatchers("/actuator/**").permitAll()
      .antMatchers("/api/explorer/**").permitAll();
//      .anyRequest().authenticated();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
