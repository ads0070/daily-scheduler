package kr.ac.deu.cse.scheduler.profile.infrastructure;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.ac.deu.cse.scheduler.profile.domain.Profile;

@Repository
public interface ProfileJpaRepository extends JpaRepository<Profile, UUID> {

	Profile findByUsername(String username);

}
