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
@NoArgsConstructor
@Table(name = "groups")
@Entity
public class Group {
	
	  @Id
	  @GeneratedValue
	  private UUID id;
	  private String groupName;
	  private String memo;
	 
	  @Builder
	  public Group(String groupName, String memo) {
	    this.groupName = groupName;
	    this.memo = memo;
	  }
}