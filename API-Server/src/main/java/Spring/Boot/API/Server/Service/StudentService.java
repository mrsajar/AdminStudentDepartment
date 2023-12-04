package Spring.Boot.API.Server.Service;

import Spring.Boot.API.Server.Entity.Student;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {

    public Student addStudent(Student student) throws Exception;

    public void deleteStudent(Long id) throws Exception;

    public List<Student> getAllStudent(String searchName, SortOrder sortOrder) throws Exception;

    public Optional<Student> getStudentById(Long id) throws Exception;

    public Student updateStudent(Student student, Long id) throws Exception;
}
