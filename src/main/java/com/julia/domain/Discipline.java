package com.julia.domain;

import org.springframework.hateoas.Link;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "discipline")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discipline_id", nullable = false)
    private Long id;
    @Column(name = "discipline_name", nullable = false, length = 45)
    private String disciplineName;
    @ManyToMany(mappedBy = "disciplines")
    private Set<Student> students;

    public Discipline(Discipline entity, Link selfLink){}
    Discipline(String disciplineName){
        this.disciplineName=disciplineName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idDiscipline) {
        this.id = idDiscipline;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discipline)) return false;
        Discipline that = (Discipline) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getDisciplineName(), that.getDisciplineName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDisciplineName());
    }
}
