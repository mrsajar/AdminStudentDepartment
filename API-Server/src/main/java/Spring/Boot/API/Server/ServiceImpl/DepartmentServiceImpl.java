package Spring.Boot.API.Server.ServiceImpl;

import Spring.Boot.API.Server.CustomException.ResourceNotFoundException;
import Spring.Boot.API.Server.Entity.Department;
import Spring.Boot.API.Server.Repository.DepartmentRepository;
import Spring.Boot.API.Server.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
            Optional<Department> byId = departmentRepository.findById(id);
            if (!byId.isEmpty()) {
                departmentRepository.deleteById(id);
            } else {
                throw new ResourceNotFoundException("Department with ID " + id + "not found");
            }
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    @Override
    public List<Department> getAllDepartment(String searchName, SortOrder sortOrder) throws Exception {
        try {
            List<Department> departments;

            if (searchName != null && !searchName.isEmpty()) {
                departments = departmentRepository.findByNameContainingIgnoreCaseOrderById(searchName);
            } else {
                departments = departmentRepository.findAll();
            }

            // Apply quicksort based on the sortOrder
            quicksort(departments, 0, departments.size() - 1, sortOrder);

            return departments;
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    private void quicksort(List<Department> departments, int low, int high, SortOrder sortOrder) {
        if (low < high) {
            int partitionIndex = partition(departments, low, high, sortOrder);

            quicksort(departments, low, partitionIndex - 1, sortOrder);
            quicksort(departments, partitionIndex + 1, high, sortOrder);
        }
    }

    private int partition(List<Department> departments, int low, int high, SortOrder sortOrder) {
        Department pivot = departments.get(high);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareDepartments(departments.get(j), pivot, sortOrder) <= 0) {
                i++;
                // Swap departments[i] and departments[j]
                Department temp = departments.get(i);
                departments.set(i, departments.get(j));
                departments.set(j, temp);
            }
        }

        // Swap departments[i + 1] and departments[high] (put the pivot element in its correct place)
        Department temp = departments.get(i + 1);
        departments.set(i + 1, departments.get(high));
        departments.set(high, temp);

        return i + 1;
    }

    private int compareDepartments(Department department1, Department department2, SortOrder sortOrder) {
        if (SortOrder.ASCENDING == sortOrder) {
            return department1.getId().compareTo(department2.getId());
        } else {
            return department2.getId().compareTo(department1.getId());
        }
    }

    @Override
    public Optional<Department> getDepartmentById(Long id) throws Exception {
        try {
            Optional<Department> findById = departmentRepository.findById(id);
            if (!findById.isEmpty()) {
                return findById;
            } else {
                throw new ResourceNotFoundException("Department with ID " + id + "not found");
            }
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
                throw new ResourceNotFoundException("Department with ID " + id + "not found");
            }
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }
}
