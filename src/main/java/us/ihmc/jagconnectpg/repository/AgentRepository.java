package us.ihmc.jagconnectpg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.ihmc.jagconnectpg.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, String>{

}