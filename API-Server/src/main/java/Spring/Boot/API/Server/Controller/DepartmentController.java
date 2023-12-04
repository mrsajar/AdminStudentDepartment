package Spring.Boot.API.Server.Controller;

import Spring.Boot.API.Server.Entity.Department;
import Spring.Boot.API.Server.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) throws Exception {
        Department save = departmentService.addDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long id) throws Exception {
        departmentService.deleteDepartment(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("delete department");
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartmentAndSearchByName(@RequestParam String searchName, SortOrder sortOrder) throws Exception {
        List<Department> allDepartment = departmentService.getAllDepartment(searchName, sortOrder);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(allDepartment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Department>> getById(@PathVariable("id") Long id) throws Exception {
        Optional<Department> departmentById = departmentService.getDepartmentById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(departmentById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long id, @RequestBody Department department) throws Exception {
        Department updated = departmentService.updateDepartment(department, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
    }
}
