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
public class UserDataMapper {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(unique = true, length = 32)
  private String username;

  @Column(nullable = false)
  private String password;
  
  @Column(nullable = false)
  private String firstName;
  
  @Column(nullable = false)
  private String lastName;
  
  @Column(nullable = false)
  private String phoneNumber;
  
  @Column(nullable = false)
  private String eMail;

  @ElementCollection(fetch = FetchType.EAGER)
  List<Authority> authorities;

  public UserDataMapper(Builder builder) {
    this.username = builder.username;
    this.password = builder.password;
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.phoneNumber = builder.phoneNumber;
    this.eMail = builder.eMail;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String eMail;

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
    
    public Builder firstName(String value) {
    	firstName = value;
        return this;
      }
    
    public Builder lastName(String value) {
    	lastName = value;
        return this;
      }
    
    public Builder phoneNumber(String value) {
        phoneNumber = value;
        return this;
      }
    
    public Builder eMail(String value) {
        eMail = value;
        return this;
      }

    public UserDataMapper build() {
      return new UserDataMapper(this);
    }
  }
}
