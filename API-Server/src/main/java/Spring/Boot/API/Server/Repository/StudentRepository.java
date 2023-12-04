package Spring.Boot.API.Server.Repository;

import Spring.Boot.API.Server.Entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public Student getById(Long id);

    public List<Student> findByNameContainingIgnoreCaseOrderById(String name);
}
