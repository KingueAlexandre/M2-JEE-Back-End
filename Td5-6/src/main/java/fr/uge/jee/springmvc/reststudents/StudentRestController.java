package fr.uge.jee.springmvc.reststudents;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class StudentRestController {

    private final Map<Long,Student> students = Map.of(0L, new Student(0, "Alexandre", "Kingué"),
                                                        1L, new Student(0, "Sami", "Bencheik"));

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable("id") long id) {
        var student = students.get(id);
        System.out.println("Student: " + id);
        if (student==null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No student with id ("+id+")");
        } else {
            return student;
        }
    }

    // Route qui renvoie la liste de tous les étudiants
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        System.out.println("Student: All");

        return students.values().stream().toList();
    }

}
