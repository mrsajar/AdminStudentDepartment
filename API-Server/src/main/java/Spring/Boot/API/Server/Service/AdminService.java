package Spring.Boot.API.Server.Service;

import Spring.Boot.API.Server.Entity.Admin;
import Spring.Boot.API.Server.Entity.Department;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public interface AdminService {

    public Admin addAdmin(Admin admin) throws Exception;

    public void deleteAdmin(Long id) throws Exception;

    public List<Admin> getAllAdmin(String searchName, SortOrder sortOrder) throws Exception;

    public Optional<Admin> getAdminById(Long id) throws Exception;

    public Admin updateAdmin(Admin admin, Long id) throws Exception;
}
