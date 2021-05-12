package kr.ac.deu.cse.scheduler.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class JwtTokenProvider {

  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String AUTHORITIES_KEY = "auth";

  private final long tokenExpirationMilliseconds;
  private final Key secretKey;

  public JwtTokenProvider() {
    this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    this.tokenExpirationMilliseconds = 60 * 60 * 1000L;
  }

  public String createToken(Authentication authentication) {
    String authorities = authentication.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.joining(","));

    Date now = new Date();
    Date expiration = new Date(now.getTime() + tokenExpirationMilliseconds);

    return Jwts.builder()
      .setSubject(authentication.getName())
      .claim(AUTHORITIES_KEY, authorities)
      .setExpiration(expiration)
      .setIssuedAt(now)
      .signWith(secretKey, SignatureAlgorithm.HS512)
      .compact();
  }

  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parserBuilder()
      .setSigningKey(secretKey)
      .build()
      .parseClaimsJws(token)
      .getBody();

    Collection<? extends GrantedAuthority> authorities =
      Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());

    User principal = new User(claims.getSubject(), "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException e) {
      log.info("Expired JWT token");
    } catch (JwtException e) {
      log.info(e.getMessage());
    }
    return false;
  }

  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
    return StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")
      ? bearerToken.substring(7)
      : null;
  }
}
