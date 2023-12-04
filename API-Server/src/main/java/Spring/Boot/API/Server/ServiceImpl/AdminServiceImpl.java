package Spring.Boot.API.Server.ServiceImpl;

import Spring.Boot.API.Server.Entity.Admin;
import Spring.Boot.API.Server.Repository.AdminRepository;
import Spring.Boot.API.Server.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin addAdmin(Admin admin) throws Exception {
        try {
            Admin saveAdmin = new Admin();
            saveAdmin.setId(admin.getId());
            saveAdmin.setName(admin.getName());
            saveAdmin.setStudent(admin.getStudent());
            Admin save = adminRepository.save(saveAdmin);
            return save;
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    @Override
    public void deleteAdmin(Long id) throws Exception {
        try {
            adminRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    @Override
    public List<Admin> getAllAdmin(String searchName, SortOrder sortOrder) throws Exception {
        try {
            Sort.Direction direction = (SortOrder.ASCENDING == sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
            List<Admin> sortSearch = adminRepository.findByNameContainingIgnoreCaseOrderById(searchName, Sort.by(direction, "id"));
            if (sortSearch != null) {
                return sortSearch;
            } else {
                List<Admin> all = adminRepository.findAll();
                return all;
            }
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    @Override
    public Optional<Admin> getAdminById(Long id) throws Exception {
        try {
            Optional<Admin> findById = adminRepository.findById(id);
            return findById;
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    @Override
    public Admin updateAdmin(Admin admin, Long id) throws Exception {
        try {
            Admin findAdmin = adminRepository.getById(id);
            if (findAdmin != null) {
                findAdmin.setId(admin.getId());
                findAdmin.setName(admin.getName());
                findAdmin.setStudent(admin.getStudent());
                Admin saved = adminRepository.save(findAdmin);
                return saved;
            } else {
                throw new RuntimeException("not found exception");
            }
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }
}
