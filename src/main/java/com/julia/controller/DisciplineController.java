package com.julia.controller;



import com.julia.DTO.DisciplineDTO;
import com.julia.domain.Discipline;
import com.julia.exceptions.ExistsStudentForDisciplinrException;
import com.julia.exceptions.NoSuchDisciplineException;
import com.julia.exceptions.NoSuchStudentException;
import com.julia.service.DisciplineService;
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
public class DisciplineController {
    @Autowired
    DisciplineService disciplineService;

    @GetMapping(value = "/api/discipline/student/{student_id}")
    public ResponseEntity<List<DisciplineDTO>> getDisciplineByStudentID(@PathVariable Long student_id) throws NoSuchStudentException, NoSuchDisciplineException{
        Set<Discipline> discipList= disciplineService.getDisciplineByStudentId(student_id);
        Link link = linkTo(methodOn(DisciplineController.class).getAllDisciplines()).withSelfRel();

        List<DisciplineDTO> disciplineDTO= new ArrayList<>();
        for (Discipline entity : discipList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            DisciplineDTO dto = new DisciplineDTO(entity, selfLink);
            disciplineDTO.add(dto);
        }

        return new ResponseEntity<>(disciplineDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/discipline/{discipline_id}")
    public ResponseEntity<DisciplineDTO> getDiscipline(@PathVariable Long discipline_id) throws NoSuchDisciplineException, NoSuchStudentException{
        Discipline discipline = disciplineService.getDiscipline(discipline_id);
        Link link = linkTo(methodOn(DisciplineController.class).getDiscipline(discipline_id)).withSelfRel();

        DisciplineDTO disciplineDTO= new DisciplineDTO(discipline, link);

        return new ResponseEntity<>(disciplineDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/discipline")
    public ResponseEntity<List<DisciplineDTO>> getAllDisciplines() throws NoSuchDisciplineException, NoSuchStudentException{
        List<Discipline> disciplineList = disciplineService.getAllDisciplines();
        Link link = linkTo(methodOn(DisciplineController.class).getAllDisciplines()).withSelfRel();

        List<DisciplineDTO> disciplineDTO= new ArrayList<>();
        for (Discipline entity : disciplineList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            DisciplineDTO dto = new DisciplineDTO(entity, selfLink);
            disciplineDTO.add(dto);
        }

        return new ResponseEntity<>(disciplineDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/discipline")
    public ResponseEntity<DisciplineDTO> addDiscipline(@RequestBody Discipline newDiscipline) throws NoSuchDisciplineException, NoSuchStudentException{
        disciplineService.createDiscipline(newDiscipline);
        Link link = linkTo(methodOn(DisciplineController.class).getDiscipline(newDiscipline.getId())).withSelfRel();

        DisciplineDTO disciplineDTO= new DisciplineDTO(newDiscipline, link);

        return new ResponseEntity<>(disciplineDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/discipline/{discipline_id}")
    public ResponseEntity<DisciplineDTO> updateDiscipline(@RequestBody Discipline uDiscipline, @PathVariable Long dicsipline_id) throws NoSuchDisciplineException, NoSuchStudentException{
        disciplineService.updateDiscipline(uDiscipline, dicsipline_id);
        Discipline discipline= disciplineService.getDiscipline(dicsipline_id);
        Link link = linkTo(methodOn(DisciplineController.class).getDiscipline(dicsipline_id)).withSelfRel();

        DisciplineDTO disciplineDTO= new DisciplineDTO(discipline, link);

        return new ResponseEntity<>(disciplineDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/discipline/{discipline_id}")
    public ResponseEntity deleteDiscipline(@PathVariable Long discipline_id) throws ExistsStudentForDisciplinrException, NoSuchDisciplineException{
        disciplineService.deleteDiscipline(discipline_id );
        return new ResponseEntity(HttpStatus.OK);
    }
}
