package us.ihmc.jagconnectpg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.ihmc.jagconnectpg.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, String>{

}