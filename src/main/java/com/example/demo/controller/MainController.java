package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.Test;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    // autowire the repositories
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TestRepository testRepository;

    // we display the home page  the list of students with their test and scores
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    // report form page
    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("tests",testRepository.findAll());
        return "reportform";
    }


    //create a student
    @RequestMapping("/addstudent")
    public String addStudent(Model model){
        model.addAttribute("student",new Student());
        return "studentform";
    }

    //save the student to the database
    @PostMapping("/savestudent")
    public String saveStudent(@ModelAttribute("student") Student student, BindingResult result){
        if(result.hasErrors()){
            return "studentform";
        }

        studentRepository.save(student);
        return "index";
    }

    // create a test
    @RequestMapping("/addtest")
    public String addTest(Model model){
        model.addAttribute("test",new Test());
        model.addAttribute("students",studentRepository.findAll());
        return "testform";
    }

    // save the test
    @PostMapping("/savetest")
    public String saveTest(@ModelAttribute("test") Test test, BindingResult result){

        if(result.hasErrors()){
            return "testform";
        }

        testRepository.save(test);

        return "index";

    }

    // search by id
    @RequestMapping("/searchstudent/")
    public String searchStudent(Model model){

         model.addAttribute("student",new Student());
         model.addAttribute("students",studentRepository.findAll());

         return "searchstudent";
    }
    @PostMapping("/searchstudent")
    public String searchStudent(Model model, HttpServletRequest request){
        String firstName=request.getParameter("fname");
        model.addAttribute("student",studentRepository. findAllStudentByFirstName(firstName));

        return "reportform";

    }
}


