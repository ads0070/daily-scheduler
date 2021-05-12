package kr.ac.deu.cse.scheduler.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter {

  public static final String AUTHORIZATION_HEADER = "Authorization";

  private final JwtTokenProvider jwtTokenProvider;

  public JwtTokenFilter(JwtTokenProvider tokenProvider) {
    this.jwtTokenProvider = tokenProvider;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
    FilterChain filterChain) throws ServletException, IOException {
    String token = jwtTokenProvider.resolveToken(request);
    try {
      if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
      SecurityContextHolder.clearContext();
    }
    filterChain.doFilter(request, response);
  }
}
