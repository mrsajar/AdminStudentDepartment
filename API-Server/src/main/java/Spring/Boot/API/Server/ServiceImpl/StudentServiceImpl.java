package Spring.Boot.API.Server.ServiceImpl;

import Spring.Boot.API.Server.CustomException.ResourceNotFoundException;
import Spring.Boot.API.Server.Entity.Student;
import Spring.Boot.API.Server.Repository.StudentRepository;
import Spring.Boot.API.Server.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) throws Exception {
        try {
            Student saveStudent = new Student();
            saveStudent.setId(student.getId());
            saveStudent.setName(student.getName());
            Student save = studentRepository.save(saveStudent);
            return save;
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    @Override
    public void deleteStudent(Long id) throws Exception {
        try {
            Optional<Student> byId = studentRepository.findById(id);
            if (!byId.isEmpty()) {
                studentRepository.deleteById(id);
            } else {
                throw new ResourceNotFoundException("Student with ID " + id + "not found");
            }
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    @Override
    public List<Student> getAllStudent(String searchName, SortOrder sortOrder) throws Exception {
        try {
            Sort.Direction direction = (SortOrder.ASCENDING == sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
            List<Student> sortSearch = studentRepository.findByNameContainingIgnoreCaseOrderById(searchName, Sort.by(direction, "id"));
            if (sortSearch != null) {
                return sortSearch;
            } else {
                List<Student> all = studentRepository.findAll();
                return all;
            }
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    @Override
    public Optional<Student> getStudentById(Long id) throws Exception {
        try {
            Optional<Student> findById = studentRepository.findById(id);
            if (!findById.isEmpty()) {
                return findById;
            } else {
                throw new ResourceNotFoundException("Student with ID " + id + "not found");
            }
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    @Override
    public Student updateStudent(Student student, Long id) throws Exception {
        try {
            Student findStudent = studentRepository.getById(id);
            if (findStudent != null) {
                findStudent.setId(student.getId());
                findStudent.setName(student.getName());
                Student saved = studentRepository.save(findStudent);
                return saved;
            } else {
                throw new ResourceNotFoundException("Student with ID " + id + "not found");
            }
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }
}
