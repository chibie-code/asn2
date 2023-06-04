package ca.sfu.cmpt276.asn2.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import ca.sfu.cmpt276.asn2.models.Student;
import ca.sfu.cmpt276.asn2.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    // GET to display all students
    @GetMapping("/students/view")
    public String getAllStudents(Model model) {
        System.out.println("Getting all students");
        // get all students
        List<Student> students = studentRepo.findAll();
        model.addAttribute("stu", students);
        return "students/showAll";
    }

    // GET to simply goto add student page
    @GetMapping("/students/add")
    public String gotoAddStudent(){
        return "students/add";
    }

    // POST request to submit new student to db
    @PostMapping("/students/add")
    public String addStudent(
            @RequestParam Map<String, String> newStudent,
            HttpServletResponse response) {
        System.out.println("ADD user");
        String newName = newStudent.get("name");
        String newWeight = newStudent.get("weight");
        String newHeight = newStudent.get("height");
        String newHairColor = newStudent.get("hairColor");
        String newGpa = newStudent.get("gpa");
        studentRepo.save(
                new Student(
                        newName,
                        Double.parseDouble(newWeight),
                        Double.parseDouble(newHeight),
                        newHairColor,
                        Double.parseDouble(newGpa)
                )
        );
        return "redirect:/students/view";
    }

    // POST request to delete student
    @PostMapping("/students/delete/{uid}")
    public String deleteStudent(
            @PathVariable(value = "uid") int uid,
            HttpServletResponse response) {
        System.out.println("DELETE user uid:");
        System.out.println(uid);
        try {
            Optional<Student> userRecord = studentRepo.findById(uid);
            if (userRecord.isPresent()) {
                System.out.println("[DELETE]Success");
                studentRepo.deleteById(uid);
                response.setStatus(205);
            }
            else {
                System.out.println("[DELETE]Record not found");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/students/view";
    }

    // this get request redirects to editing page
    @GetMapping("/students/edit/{uid}")
    public String gotoEditStudent(
            @PathVariable(value = "uid") int uid,
            Model model,
            HttpServletResponse response) {
        // get all students from db
        Student student;
        try {
            student = studentRepo.findById(uid).orElseThrow(() -> (new Exception("null")));
            // end of db call
            model.addAttribute("stu", student);
            model.addAttribute("stuId", uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // end of db call
        return "students/edit";
    }

    // this endpoint is for submitting an edit and redirects to showAll page
    @PostMapping("/students/edit/{uid}")
    public String editStudent(
            @PathVariable(value = "uid") int uid,
            @RequestParam Map<String, String> newStudent,
            HttpServletResponse response) {
        System.out.println("EDIT student uid:");
        System.out.println(uid);
        String newName = newStudent.get("newName");
        double newWeight = Double.parseDouble(newStudent.get("newWeight"));
        double newHeight = Double.parseDouble(newStudent.get("newHeight"));
        String newHairColor = newStudent.get("newHairColor");
        double newGpa = Double.parseDouble(newStudent.get("newGpa"));
        Student student;
        try {
            student = studentRepo.findById(uid).orElseThrow(() -> (new Exception("null")));
            student.setName(newName);
            student.setWeight(newWeight);
            student.setHeight(newHeight);
            student.setHairColor(newHairColor);
            student.setGpa(newGpa);
            studentRepo.save(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // end of db call
        return "redirect:/students/view";
    }
}

