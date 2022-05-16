package us.ihmc.jagconnectpg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.ihmc.jagconnectpg.model.JagCell;

@Repository
public interface JagCellRepository extends JpaRepository<JagCell, String>{

}