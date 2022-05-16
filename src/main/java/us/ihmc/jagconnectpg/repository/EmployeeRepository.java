package us.ihmc.jagconnectpg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.ihmc.jagconnectpg.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}