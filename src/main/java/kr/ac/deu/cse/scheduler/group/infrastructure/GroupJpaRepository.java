package kr.ac.deu.cse.scheduler.group.infrastructure;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.ac.deu.cse.scheduler.group.domain.Group;

@Repository
public interface GroupJpaRepository extends JpaRepository<Group, UUID> {
	Group findByGroupName(String groupName);
}
