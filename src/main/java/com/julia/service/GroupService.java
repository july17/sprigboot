package com.julia.service;

import com.julia.Repository.GroupRepository;
import com.julia.Repository.StudentRepository;
import com.julia.domain.Group;
import com.julia.exceptions.ExistsStudentForGroupException;
import com.julia.exceptions.NoSuchGroupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;
    private boolean ascending;

    @Autowired
    StudentRepository studentRepository;

    public List<Group> getAllCity() {
        return groupRepository.findAll();
    }

    public Group getGroup(Long city_id) throws NoSuchGroupException{
//        City city =cityRepository.findOne(city_id);//1.5.9
        Group group = groupRepository.findById(city_id).get();//2.0.0.M7
        if (group == null) throw new NoSuchGroupException();
        return group;
    }

    @Transactional
    public void createGroup(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public void updateGroup(Group uGroup, Long group_id) throws NoSuchGroupException {
//        City city = cityRepository.findOne(city_id);//1.5.9
        Group group = groupRepository.findById(group_id).get();//2.0.0.M7

        if (group == null) throw new NoSuchGroupException();
        group.setGroup_name(uGroup.getGroup_name());
        groupRepository.save(group);
    }

    @Transactional
    public void deleteGroup(Long group_id) throws NoSuchGroupException, ExistsStudentForGroupException{
//        City city = cityRepository.findOne(city_id);//1.5.9
        Group group = groupRepository.findById(group_id).get();//2.0.0.M7
        if (group == null) throw new NoSuchGroupException();
        if (group.getStudents().size() != 0) throw new ExistsStudentForGroupException();
        groupRepository.delete(group);
    }


}
