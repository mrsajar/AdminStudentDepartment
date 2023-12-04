package Spring.Boot.API.Server.ServiceImpl;

import Spring.Boot.API.Server.Entity.Department;
import Spring.Boot.API.Server.Repository.DepartmentRepository;
import Spring.Boot.API.Server.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department addDepartment(Department department) throws Exception {
        try {
            Department saveDepartment = new Department();
            saveDepartment.setId(department.getId());
            saveDepartment.setName(department.getName());
            saveDepartment.setAdmin(department.getAdmin());
            Department save = departmentRepository.save(department);
            return save;
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    @Override
    public void deleteDepartment(Long id) throws Exception {
        try {
            departmentRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    @Override
    public List<Department> getAllDepartment(String searchName, SortOrder sortOrder) throws Exception {
        try {
            Sort.Direction direction = (SortOrder.ASCENDING == sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
            List<Department> sortSearch = departmentRepository.findByNameContainingIgnoreCaseOrderById(searchName, Sort.by(direction, "id"));
            if (sortSearch != null) {
                return sortSearch;
            } else {
                List<Department> all = departmentRepository.findAll();
                return all;
            }
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    @Override
    public Optional<Department> getDepartmentById(Long id) throws Exception {
        try {
            Optional<Department> findById = departmentRepository.findById(id);
            return findById;
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    @Override
    public Department updateDepartment(Department department, Long id) throws Exception {
        try {
            Department findDepartment = departmentRepository.getById(id);
            if (findDepartment != null) {
                findDepartment.setId(department.getId());
                findDepartment.setName(department.getName());
                findDepartment.setAdmin(department.getAdmin());
                Department saved = departmentRepository.save(findDepartment);
                return saved;
            } else {
                throw new RuntimeException("not found exception");
            }
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }
}
