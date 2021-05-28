package kr.ac.deu.cse.scheduler.group.presentation;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.ac.deu.cse.scheduler.group.application.GroupService;
import kr.ac.deu.cse.scheduler.group.domain.Group;
import kr.ac.deu.cse.scheduler.group.domain.GroupRequest;
import kr.ac.deu.cse.scheduler.group.domain.GroupResponse;

@RequestMapping("/groups")
@RepositoryRestController
public class GroupController {
	
	private final GroupService groupService;

	  @Autowired
	  public GroupController(GroupService groupService) {
	    this.groupService = groupService;
	  }

	  @ResponseBody
	  @PostMapping
	  public GroupResponse createGroup(@RequestBody GroupRequest userRequest) {
	    return groupService.createGroup(userRequest);
	  }

	  @ResponseBody
	  @GetMapping
	  public List<Group> getAllGroups() {
	    return groupService.getAllGroups();
	  }

	  @ResponseBody
	  @GetMapping("/{id}")
	  public GroupResponse getOneUser(@PathVariable UUID id) {
	    return groupService.getGroupById(id);
	  }

	  @ResponseBody
	  @PutMapping("/{id}")
	  public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody GroupRequest request) {
		  GroupResponse response = groupService.updateGroupById(id, request);

		  return ResponseEntity.ok(response);
	  }

	  @DeleteMapping("/{id}")
	  public void deleteUser(@PathVariable UUID id) {
		  groupService.deleteGroupById(id);
	  }
	
}