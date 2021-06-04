package kr.ac.deu.cse.scheduler.profile.domain;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ProfileResponse {
	
	UUID id;
	String username;
	String path;
	String originName;
	String saveFileName;
	
}
