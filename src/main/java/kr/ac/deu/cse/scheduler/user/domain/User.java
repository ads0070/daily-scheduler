package kr.ac.deu.cse.scheduler.user.domain;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import kr.ac.deu.cse.scheduler.auth.domain.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(unique = true, length = 32)
  private String username;

  @Column(nullable = false)
  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  List<Authority> authorities;

  // @Builder
  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User(Builder builder) {
    this.username = builder.username;
    this.password = builder.password;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String username;
    private String password;

    Builder() {
    }

    public Builder username(String value) {
      username = value;
      return this;
    }

    public Builder password(String value) {
      password = value;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}
