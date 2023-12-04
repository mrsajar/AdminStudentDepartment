package Spring.Boot.API.Server.ServiceImpl;

import Spring.Boot.API.Server.CustomException.ResourceNotFoundException;
import Spring.Boot.API.Server.Entity.Admin;
import Spring.Boot.API.Server.Repository.AdminRepository;
import Spring.Boot.API.Server.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
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
            Optional<Admin> byId = adminRepository.findById(id);
            if (!byId.isEmpty()) {
                adminRepository.deleteById(id);
            } else {
                throw new ResourceNotFoundException("Admin with ID " + id + "not found");
            }
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    public List<Admin> getAllAdmin(String searchName, SortOrder sortOrder) throws Exception {
        try {
            List<Admin> admins;

            if (searchName != null && !searchName.isEmpty()) {
                admins = adminRepository.findByNameContainingIgnoreCaseOrderById(searchName);
            } else {
                admins = adminRepository.findAll();
            }

            // Apply quicksort based on the sortOrder
            quicksort(admins, 0, admins.size() - 1, sortOrder);

            return admins;
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    private void quicksort(List<Admin> admins, int low, int high, SortOrder sortOrder) {
        if (low < high) {
            int partitionIndex = partition(admins, low, high, sortOrder);

            quicksort(admins, low, partitionIndex - 1, sortOrder);
            quicksort(admins, partitionIndex + 1, high, sortOrder);
        }
    }

    private int partition(List<Admin> admins, int low, int high, SortOrder sortOrder) {
        Admin pivot = admins.get(high);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareAdmins(admins.get(j), pivot, sortOrder) <= 0) {
                i++;
                // Swap admins[i] and admins[j]
                Admin temp = admins.get(i);
                admins.set(i, admins.get(j));
                admins.set(j, temp);
            }
        }

        // Swap admins[i + 1] and admins[high] (put the pivot element in its correct place)
        Admin temp = admins.get(i + 1);
        admins.set(i + 1, admins.get(high));
        admins.set(high, temp);

        return i + 1;
    }

    private int compareAdmins(Admin admin1, Admin admin2, SortOrder sortOrder) {
        if (SortOrder.ASCENDING == sortOrder) {
            return admin1.getId().compareTo(admin2.getId());
        } else {
            return admin2.getId().compareTo(admin1.getId());
        }
    }

    @Override
    public Optional<Admin> getAdminById(Long id) throws Exception {
        try {
            Optional<Admin> findById = adminRepository.findById(id);
            if (!findById.isEmpty()) {
                return findById;
            } else {
                throw new ResourceNotFoundException("Admin with ID " + id + "not found");
            }
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
                throw new ResourceNotFoundException("Admin with ID " + id + "not found");
            }
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }
}
