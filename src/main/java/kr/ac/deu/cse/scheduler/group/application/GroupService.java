package kr.ac.deu.cse.scheduler.group.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.ac.deu.cse.scheduler.group.domain.Group;
import kr.ac.deu.cse.scheduler.group.domain.GroupRequest;
import kr.ac.deu.cse.scheduler.group.domain.GroupResponse;
import kr.ac.deu.cse.scheduler.group.infrastructure.GroupJpaRepository;

@Service
public class GroupService {
	private final GroupJpaRepository groupRepository;
	
	@Autowired
	  public GroupService(GroupJpaRepository groupRepository) {
	    this.groupRepository = groupRepository;
	  }

	  @Transactional
	  public GroupResponse createGroup(final GroupRequest groupRequest) {
	    Group group = Group.builder().groupName(groupRequest.getGroupName()).memo(groupRequest.getMemo()).build();
	    
	    group = groupRepository.save(group);
	    return GroupResponse.builder()
	    		.id(group.getId())
	    		.groupName(group.getGroupName())
	    		.memo(group.getMemo())
	    		.build();
	  }

	  public List<Group> getAllGroups() {
	    return groupRepository.findAll();
	  }

	  public GroupResponse getGroupById(UUID id) {
	    Optional<Group> group = groupRepository.findById(id);

	    if (group.isEmpty()) {
	      throw new RuntimeException(String.format("User id %s is none", id));
	    }

	    return GroupResponse.builder()
	    		.id(group.get().getId())
				.groupName(group.get().getGroupName())
				.memo(group.get().getMemo())
				.build();
	    }

	   @Transactional
	   public GroupResponse updateGroupById(UUID id, final GroupRequest newGroup) {
		   Group updatedGroup = groupRepository.findById(id)
				   .map(group -> {
					   group.setGroupName(newGroup.getGroupName());
					   group.setMemo(newGroup.getMemo());
					   return groupRepository.save(group);
				   }).orElseThrow();
		return GroupResponse.builder()
				.id(updatedGroup.getId())
				.groupName(updatedGroup.getGroupName())
				.memo(updatedGroup.getMemo())
				.build();
	   }

	  @Transactional
	  public void deleteGroupById(UUID id) {
		  groupRepository.deleteById(id);
	  }
}