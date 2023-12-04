package Spring.Boot.API.Server.Repository;

import Spring.Boot.API.Server.Entity.Admin;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    public Admin getById(Long id);

    public List<Admin> findByNameContainingIgnoreCaseOrderById(String name);


}
