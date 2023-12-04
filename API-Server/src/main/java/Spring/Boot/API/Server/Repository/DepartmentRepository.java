package Spring.Boot.API.Server.Repository;

import Spring.Boot.API.Server.Entity.Department;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public Department getById(Long id);

    public List<Department> findByNameContainingIgnoreCaseOrderById(String name);
}
