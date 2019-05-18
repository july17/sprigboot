package com.julia.domain;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "logger")
public class Logger {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logger_id", nullable = false)
    private Long id;
    @Column(name = "student", nullable = false, length = 50)
    private String student;
    @Column(name = "discipline", nullable = false, length = 90)
    private String discipline;
    @Column(name = "Action", nullable = false, length = 10)
    private String action;
    @Column(name = "time_stamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    @Column(name = "user", nullable = true, length = 50)
    private String user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Logger)) return false;
        Logger logger = (Logger) o;
        return Objects.equals(getId(), logger.getId()) &&
                Objects.equals(getStudent(), logger.getStudent()) &&
                Objects.equals(getDiscipline(), logger.getDiscipline()) &&
                Objects.equals(getAction(), logger.getAction()) &&
                Objects.equals(getTimeStamp(), logger.getTimeStamp()) &&
                Objects.equals(getUser(), logger.getUser());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getStudent(), getDiscipline(), getAction(), getTimeStamp(), getUser());
    }
}

