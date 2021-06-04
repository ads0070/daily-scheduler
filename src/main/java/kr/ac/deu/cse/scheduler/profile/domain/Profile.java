package kr.ac.deu.cse.scheduler.profile.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "profiles")
@Entity
public class Profile {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@Column
	private String username;
	private String path;
	private String originName;
	private String saveFileName;
	
	@Builder
	public Profile(String username, String path, String originName, String saveFileName) {
		this.username = username;
		this.path = path;
		this.originName = originName;
		this.saveFileName = saveFileName;
	}
	
}
