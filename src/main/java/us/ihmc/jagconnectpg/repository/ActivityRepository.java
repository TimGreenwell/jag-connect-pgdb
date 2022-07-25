package us.ihmc.jagconnectpg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.ihmc.jagconnectpg.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String>{

}