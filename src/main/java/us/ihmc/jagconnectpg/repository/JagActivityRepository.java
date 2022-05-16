package us.ihmc.jagconnectpg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.ihmc.jagconnectpg.model.JagActivity;

@Repository
public interface JagActivityRepository extends JpaRepository<JagActivity, String>{

}