package kr.ac.deu.cse.scheduler.user.domain;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
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

  @Embedded
  private UserState state;

  public User(Builder builder) {
    this.username = builder.username;
    this.password = builder.password;
    this.state = builder.state;
  }

  public void setUserState(UserState state) {
    this.state = state;
  }

  public UserResponse getUserEntity() {
    return this.state.getUserEntity(this);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String username;
    private String password;
    private UserState state;

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

    public Builder state(UserState value) {
      state = value;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}
