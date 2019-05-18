package com.julia.controller;


import com.julia.DTO.StudentDTO;
import com.julia.domain.Student;
import com.julia.exceptions.*;
import com.julia.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
    public class StudentController {
        @Autowired
        StudentService studentService;

        @GetMapping(value = "/api/student/group/{group_id}")
        public ResponseEntity<List<StudentDTO>> getStudentByGroupID(@PathVariable Long group_id) throws NoSuchGroupException, NoSuchStudentException, NoSuchDisciplineException{
            List<Student> studentList = studentService.getStudentByGroupId(group_id);

            Link link = linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel();

            List<StudentDTO> studentDTO = new ArrayList<>();
            for (Student entity : studentList) {
                Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
                StudentDTO dto = new StudentDTO(entity, selfLink);
                studentDTO.add(dto);
            }

            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        }

        @GetMapping(value = "/api/student/{student_id}")
        public ResponseEntity<StudentDTO>getStudent(@PathVariable Long student_id) throws NoSuchStudentException, NoSuchDisciplineException{
            Student student= studentService.getStudent(student_id);
            Link link = linkTo(methodOn(StudentController.class).getStudent(student_id)).withSelfRel();

            StudentDTO studentDTO= new StudentDTO(student, link);

            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        }

        @GetMapping(value = "/api/student")
        public ResponseEntity<List<StudentDTO>> getAllStudents() throws NoSuchStudentException, NoSuchDisciplineException{
            List<Student> studentList= studentService.getAllStudent();
            Link link = linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel();

            List<StudentDTO> studentDTO= new ArrayList<>();
            for (Student entity : studentList) {
                Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
                StudentDTO dto = new StudentDTO(entity, selfLink);
                studentDTO.add(dto);
            }

            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        }

        @GetMapping(value = "/api/student/discipline/{discipline_id}")
        public ResponseEntity<List<StudentDTO>> getStudentsByDisciplineID(@PathVariable Long discipline_id) throws NoSuchDisciplineException, NoSuchStudentException{
            Set<Student> studentList= studentService.getStudentByDisciplineId(discipline_id);
            Link link = linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel();

            List<StudentDTO> studentDTO= new ArrayList<>();
            for (Student entity : studentList) {
                Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
                StudentDTO dto = new StudentDTO(entity, selfLink);
                studentDTO.add(dto);
            }

            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        }

        @PostMapping(value = "/api/student/group/{group_id}")
        public  ResponseEntity<StudentDTO> addStudent(@RequestBody Student newStudent, @PathVariable Long group_id)
                throws NoSuchGroupException, NoSuchStudentException, NoSuchDisciplineException{
            studentService.createStudent(newStudent, group_id);
            Link link = linkTo(methodOn(StudentController.class).getStudent(newStudent.getId())).withSelfRel();

            StudentDTO studentDTO = new StudentDTO(newStudent, link);

            return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
        }

        @PutMapping(value = "/api/student/{student_id}/group/{Group_id}")
        public  ResponseEntity<StudentDTO> updateStudent(@RequestBody Student uStudent,
                                                       @PathVariable Long student_id, @PathVariable Long group_id)
                throws NoSuchGroupException, NoSuchStudentException, NoSuchDisciplineException{
            studentService.updateStudent(uStudent, student_id, group_id);
            Student student = studentService.getStudent(student_id);
            Link link = linkTo(methodOn(StudentController.class).getStudent(student_id)).withSelfRel();

            StudentDTO studentDTO= new StudentDTO(student, link);

            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        }

        @DeleteMapping(value = "/api/student/{student_id}")
        public  ResponseEntity deleteSrudent(@PathVariable Long student_id) throws NoSuchStudentException, ExistsDisciplineForStudentException{
            studentService.deleteStudent(student_id);
            return new ResponseEntity(HttpStatus.OK);
        }

        @PostMapping(value = "/api/student/{student_id}/discipline/{discipline_id}")
        public  ResponseEntity<StudentDTO> addDisciplineForStudent(@PathVariable Long student_id, @PathVariable Long discipline_id)
                throws NoSuchStudentException, NoSuchDisciplineException, AlreadyExistsDisciplineInStudentException, DisciplineAbsentException{
            studentService.addDisciplinesForStudent(student_id, discipline_id );
            Student student= studentService.getStudent(student_id);
            Link link = linkTo(methodOn(StudentController.class).getStudent(student_id)).withSelfRel();

            StudentDTO studentDTO= new StudentDTO(student, link);

            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        }

        @DeleteMapping(value = "/api/student/{student_id}/discipline/{discipline_id}")
        public  ResponseEntity<StudentDTO> removeDisciplineForStudent(@PathVariable Long student_id, @PathVariable Long discipline_id)
                throws NoSuchStudentException, NoSuchDisciplineException, StudentHasNotDisciplineException{
            studentService.removeDisciplineForStudent(student_id, discipline_id);
            Student student= studentService.getStudent(student_id);
            Link link = linkTo(methodOn(StudentController.class).getStudent(student_id)).withSelfRel();

            StudentDTO studentDTO= new StudentDTO(student, link);

            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        }

    }
