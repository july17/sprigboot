package com.julia.service;

import com.julia.Repository.DisciplineRepository;
import com.julia.Repository.GroupRepository;
import com.julia.Repository.StudentRepository;
import com.julia.domain.Discipline;
import com.julia.domain.Group;
import com.julia.domain.Student;
import com.julia.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.Style;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    DisciplineRepository disciplineRepository;

    public List<Student> getStudentByGroupId(Long group_id) throws NoSuchGroupException {
//        City city = cityRepository.findOne(city_id);//1.5.9
        Group group = groupRepository.findById(group_id).get();//2.0.0.M7
        if (group == null) throw new NoSuchGroupException();
        return group.getStudents();
    }

    public Student getStudent(Long student_id) throws NoSuchStudentException {
//        Person person = personRepository.findOne(person_id);//1.5.9
        Student student= studentRepository.findById(student_id).get();//2.0.0.M7
        if (student == null) throw new NoSuchStudentException();
        return student;
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();

    }

    public Set<Student> getStudentByDisciplineId(Long book_id) throws NoSuchDisciplineException{
//        Book book = bookRepository.findOne(book_id);//1.5.9
        Discipline discipline = disciplineRepository.findById(book_id).get();//2.0.0.M7
        if (discipline == null) throw new NoSuchDisciplineException();
        return discipline.getStudents();
    }

    @Transactional
    public void createStudent(Student student, Long group_id) throws NoSuchGroupException{
        if (group_id > 0) {
//            City city = cityRepository.findOne(city_id);//1.5.9
            Group group = groupRepository.findById(group_id).get();//2.0.0.M7
            if (group == null) throw new NoSuchGroupException();
            student.setGroups(group);
        }
        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Student uStudent, Long student_id, Long group_id) throws NoSuchGroupException, NoSuchStudentException{
//        City city = cityRepository.findOne(city_id);//1.5.9
        Group group = groupRepository.findById(group_id).get();//2.0.0.M7
        if (group_id > 0) {
            if (group == null) throw new NoSuchGroupException();
        }
//        Person person = personRepository.findOne(person_id);//1.5.9
        Student student= studentRepository.findById(student_id).get();//2.0.0.M7
        if (student == null) throw new NoSuchStudentException();
        //update
        student.setSurname(uStudent.getSurname());
        student.setName(uStudent.getName());
        student.setEmail(uStudent.getEmail());
        student.setCity(uStudent.getCity());
        if (group_id > 0) student.setGroups(group);
        else student.setGroups(null);
        studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long student_id) throws NoSuchStudentException, ExistsDisciplineForStudentException{
//        Person person = personRepository.findOne(person_id);//1.5.9
        Student student = studentRepository.findById(student_id).get();//2.0.0.M7
        if (student == null) throw new NoSuchStudentException();
        if (student.getDisciplines().size() != 0) throw new ExistsDisciplineForStudentException();
        studentRepository.delete(student);
    }

    @Transactional
    public void addDisciplinesForStudent(Long student_id, Long disciplines_id)
            throws NoSuchStudentException, NoSuchDisciplineException, AlreadyExistsDisciplineInStudentException, DisciplineAbsentException{
//        Person person = personRepository.findOne(person_id);//1.5.9
        Student student= studentRepository.findById(student_id).get();//2.0.0.M7
        if (student == null) throw new NoSuchStudentException();
//        Book book = bookRepository.findOne(book_id);//1.5.9
        Discipline discipline= disciplineRepository.findById(disciplines_id).get();//2.0.0.M7
        if (discipline == null) throw new NoSuchDisciplineException();
        if (student.getDisciplines().contains(discipline) == true) throw new AlreadyExistsDisciplineInStudentException();
        student.getDisciplines().add(discipline);
        studentRepository.save(student);
    }

    @Transactional
    public void removeDisciplineForStudent(Long student_id, Long discipline_id)
            throws NoSuchStudentException, NoSuchDisciplineException, StudentHasNotDisciplineException{
//        Person person = personRepository.findOne(person_id);//1.5.9
        Student student= studentRepository.findById(student_id).get();//2.0.0.M7
        if (student == null) throw new NoSuchStudentException();
//        Book book = bookRepository.findOne(book_id);//1.5.9
        Discipline discipline= disciplineRepository.findById(discipline_id).get();//2.0.0.M7
        if (discipline == null) throw new NoSuchDisciplineException();
        if (student.getDisciplines().contains(discipline) == false) throw new StudentHasNotDisciplineException();
        student.getDisciplines().remove(discipline);
        studentRepository.save(student);
    }
}
