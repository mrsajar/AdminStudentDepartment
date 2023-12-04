package Spring.Boot.API.Server.ServiceImpl;

import Spring.Boot.API.Server.CustomException.ResourceNotFoundException;
import Spring.Boot.API.Server.Entity.Student;
import Spring.Boot.API.Server.Repository.StudentRepository;
import Spring.Boot.API.Server.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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


    public List<Student> getAllStudent(String searchName, SortOrder sortOrder) throws Exception {
        try {
            List<Student> students;

            if (searchName != null && !searchName.isEmpty()) {
                students = studentRepository.findByNameContainingIgnoreCaseOrderById(searchName);
            } else {
                students = studentRepository.findAll();
            }

            // Apply quicksort based on the sortOrder
            quicksort(students, 0, students.size() - 1, sortOrder);

            return students;
        } catch (Exception e) {
            throw new Exception("Exception is " + e);
        }
    }

    private void quicksort(List<Student> students, int low, int high, SortOrder sortOrder) {
        if (low < high) {
            int partitionIndex = partition(students, low, high, sortOrder);

            quicksort(students, low, partitionIndex - 1, sortOrder);
            quicksort(students, partitionIndex + 1, high, sortOrder);
        }
    }

    private int partition(List<Student> students, int low, int high, SortOrder sortOrder) {
        Student pivot = students.get(high);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareStudents(students.get(j), pivot, sortOrder) <= 0) {
                i++;
                // Swap students[i] and students[j]
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }

        // Swap students[i + 1] and students[high] (put the pivot element in its correct place)
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);

        return i + 1;
    }

    private int compareStudents(Student student1, Student student2, SortOrder sortOrder) {
        if (SortOrder.ASCENDING == sortOrder) {
            return student1.getId().compareTo(student2.getId());
        } else {
            return student2.getId().compareTo(student1.getId());
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
