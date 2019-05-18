package com.julia.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Student")
public class Student {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long id;
    @Column(name = "surname", nullable = false, length = 25)
    private String surname;
    @Column(name = "name", nullable = false, length = 25)
    private String name;
    @Column(name = "email", nullable = true, length = 45)
    private String email;
    @Column(name = "city", nullable = true, length = 45)
    private String city;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group groups;
    @ManyToMany
    @JoinTable(name = "student_discipline",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "discipline_id", referencedColumnName = "discipline_id", nullable = false))
    private Set<Discipline> disciplines;

    Student(){}
    Student(String surname, String name, String email, String city){
        this.surname=surname;
        this.name=name;
        this.email=email;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idStudent) {
        this.id = idStudent;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Group getGroups() {
        return groups;
    }

    public void setGroups(Group groups) {
        this.groups = groups;
    }

    public Set<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(Set<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId()) &&
                Objects.equals(getSurname(), student.getSurname()) &&
                Objects.equals(getName(), student.getName()) &&
                Objects.equals(getEmail(), student.getEmail()) &&
                Objects.equals(getCity(), student.getCity());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getSurname(), getName(), getEmail(), getCity());
    }
}
