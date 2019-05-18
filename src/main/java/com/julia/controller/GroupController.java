package com.julia.controller;

import com.julia.DTO.GroupDTO;
import com.julia.domain.Group;
import com.julia.exceptions.ExistsStudentForGroupException;
import com.julia.exceptions.NoSuchDisciplineException;
import com.julia.exceptions.NoSuchGroupException;
import com.julia.exceptions.NoSuchStudentException;
import com.julia.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class GroupController {
    @Autowired
    GroupService groupService;

    @GetMapping(value = "/api/group")
    public ResponseEntity<List<GroupDTO>> getAllGroups() throws  NoSuchDisciplineException, NoSuchGroupException, NoSuchStudentException {
        List<Group> groupsList = groupService.getAllCity();
        Link link = linkTo(methodOn(GroupController.class).getAllGroups()).withSelfRel();

        List<GroupDTO> groupDTO= new ArrayList<>();
        for (Group entity : groupsList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            GroupDTO dto = new GroupDTO(entity, selfLink);
            groupDTO.add(dto);
        }

        return new ResponseEntity<>(groupDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/group/{group_id}")
    public ResponseEntity<GroupDTO> getGroup(@PathVariable Long group_id) throws NoSuchGroupException, NoSuchStudentException, NoSuchDisciplineException{
        Group group= groupService.getGroup(group_id);
        Link link = linkTo(methodOn(GroupController.class).getGroup(group_id)).withSelfRel();

        GroupDTO groupDTO= new GroupDTO(group, link);

        return new ResponseEntity<>(groupDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/group/{group_id}")
    public ResponseEntity<GroupDTO> addGroup(@RequestBody Group newGroup) throws NoSuchGroupException, NoSuchStudentException, NoSuchDisciplineException{
        groupService.createGroup(newGroup);
        Link link = linkTo(methodOn(GroupController.class).getGroup(newGroup.getId())).withSelfRel();

        GroupDTO groupDTO= new GroupDTO(newGroup, link);

        return new ResponseEntity<>(groupDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/group/{group_id}")
    public ResponseEntity<GroupDTO> updateGroup(@RequestBody Group uGroup, @PathVariable Long group_id) throws NoSuchGroupException, NoSuchStudentException, NoSuchDisciplineException{
        groupService.updateGroup(uGroup, group_id);
        Group group= groupService.getGroup(group_id);
        Link link = linkTo(methodOn(GroupController.class).getGroup(group_id)).withSelfRel();

        GroupDTO groupDTO= new GroupDTO(group, link);

        return new ResponseEntity<>(groupDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/group/{group_id}")
    public ResponseEntity deleteGroup(@PathVariable Long group_id) throws NoSuchGroupException, ExistsStudentForGroupException{
        groupService.deleteGroup(group_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
