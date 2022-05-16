package us.ihmc.jagconnectpg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.ihmc.jagconnectpg.model.Analysis;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, String>{

}