package com.julia.DTO;

import com.julia.controller.StudentController;
import com.julia.domain.Group;
import com.julia.exceptions.NoSuchDisciplineException;
import com.julia.exceptions.NoSuchGroupException;
import com.julia.exceptions.NoSuchStudentException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class GroupDTO extends ResourceSupport {
    Group group;
    public GroupDTO(Group group, Link selfLink) throws NoSuchStudentException, NoSuchDisciplineException, NoSuchGroupException{
        this.group=group;
        add(selfLink);
        add(linkTo(methodOn(StudentController.class).getStudentByGroupID(group.getId())).withRel("students"));
    }

    public Long getGroupId() { return group.getId(); }

    public String getGroup_name() {
        return group.getGroup_name();
    }
}
