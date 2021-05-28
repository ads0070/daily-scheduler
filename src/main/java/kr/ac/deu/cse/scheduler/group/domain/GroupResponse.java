package kr.ac.deu.cse.scheduler.group.domain;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class GroupResponse {
	UUID id;
	String groupName;
	String memo;
}