package kr.ac.deu.cse.scheduler.group.domain;

import java.util.UUID;
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
@Table(name = "groups")
@Entity
public class Group {
	
	  @Id
	  @GeneratedValue
	  private UUID id;
	  private String groupName;
	 
	  @Builder
	  public Group(String groupName) {
	    this.groupName = groupName;
	  }
}