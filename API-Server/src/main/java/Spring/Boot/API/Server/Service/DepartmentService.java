package Spring.Boot.API.Server.Service;

import Spring.Boot.API.Server.Entity.Department;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public interface DepartmentService {
    public Department addDepartment(Department department) throws Exception;

    public void deleteDepartment(Long id) throws Exception;

    public List<Department> getAllDepartment(String searchName, SortOrder sortOrder) throws Exception;

    public Optional<Department> getDepartmentById(Long id) throws Exception;

    public Department updateDepartment(Department department, Long id) throws Exception;
}
