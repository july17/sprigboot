package com.julia.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "studentgroups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Long id;
    @Column(name = "group_name", nullable = false, length = 25)
    private String group_name;
    @OneToMany(mappedBy = "groups")
    private List<Student> students;

    public Long getId() {
        return id;
    }

    public void setId(Long idGroup) {
        this.id = idGroup;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return Objects.equals(getId(), group.getId()) &&
                Objects.equals(getGroup_name(), group.getGroup_name());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getGroup_name());
    }
}
