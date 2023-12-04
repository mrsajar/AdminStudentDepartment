package Spring.Boot.API.Server.Controller;

import Spring.Boot.API.Server.Entity.Student;
import Spring.Boot.API.Server.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) throws Exception {
        Student save = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) throws Exception {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("delete department");
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudentAndSearchByName(@RequestParam String searchName, SortOrder sortOrder) throws Exception {
        List<Student> allStudent = studentService.getAllStudent(searchName, sortOrder);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(allStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getById(@PathVariable("id") Long id) throws Exception {
        Optional<Student> studentById = studentService.getStudentById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updatedStudent(@PathVariable("id") Long id, @RequestBody Student student) throws Exception {
        Student updated = studentService.updateStudent(student, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
    }
}
