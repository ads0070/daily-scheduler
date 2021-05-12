package kr.ac.deu.cse.scheduler.auth.presentation;

import kr.ac.deu.cse.scheduler.auth.domain.AuthRequest;
import kr.ac.deu.cse.scheduler.auth.domain.AuthResponse;
import kr.ac.deu.cse.scheduler.security.JwtTokenFilter;
import kr.ac.deu.cse.scheduler.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final JwtTokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;

  public AuthController(JwtTokenProvider tokenProvider,
    AuthenticationManagerBuilder authenticationManagerBuilder) {
    this.tokenProvider = tokenProvider;
    this.authenticationManagerBuilder = authenticationManagerBuilder;
  }

  @PostMapping
  public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest authRequest) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
      authRequest.getUsername(), authRequest.getPassword());

    Authentication authentication = authenticationManagerBuilder.getObject()
      .authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = tokenProvider.createToken(authentication);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JwtTokenFilter.AUTHORIZATION_HEADER, "Bearer " + token);

    return new ResponseEntity<>(new AuthResponse(token), httpHeaders, HttpStatus.OK);
  }
}
