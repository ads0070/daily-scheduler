package kr.ac.deu.cse.scheduler.profile.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.ac.deu.cse.scheduler.profile.domain.Profile;
import kr.ac.deu.cse.scheduler.profile.domain.ProfileResponse;
import kr.ac.deu.cse.scheduler.profile.infrastructure.ProfileJpaRepository;

@Service
public class ProfileService {
	private final ProfileJpaRepository profileRepository;
	
	@Autowired
	public ProfileService(ProfileJpaRepository profileRepository) {
		this.profileRepository = profileRepository;
	}
	
	public ProfileResponse createProfile(String username, String p, String oriName, String saveName) {
		Profile profile = Profile.builder().username(username)
										   .path(p)
										   .originName(oriName)
										   .saveFileName(saveName)
										   .build();
		System.out.println(profile);
		profile = profileRepository.save(profile);
		
		return ProfileResponse.builder()
				.id(profile.getId())
				.username(profile.getUsername())
				.path(profile.getPath())
				.originName(profile.getOriginName())
				.saveFileName(profile.getSaveFileName())
				.build();
	}
	
	public String getProfileByUsername(String username) {
		Profile profile = profileRepository.findByUsername(username);
		return profile.getPath()+profile.getSaveFileName();
	}
	
}