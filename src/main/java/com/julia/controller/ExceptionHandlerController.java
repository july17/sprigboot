package com.julia.controller;

import com.julia.DTO.MessageDTO;
import com.julia.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchGroupException.class)
    ResponseEntity<MessageDTO> handleNoSushCityException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such group not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchStudentException.class)
    ResponseEntity<MessageDTO> handleNoSushPersonException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such student not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchDisciplineException.class)
    ResponseEntity<MessageDTO> handleNoSushDisciplineException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such discipline not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistsStudentForGroupException.class)
    ResponseEntity<MessageDTO> handleExistsPersonsForCityException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are students for this group"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsDisciplineForStudentException.class)
    ResponseEntity<MessageDTO> handleExistsBooksForPersonException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are discipline for this students"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsStudentForDisciplinrException.class)
    ResponseEntity<MessageDTO> handleExistsPersonsForBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are students for this discipline"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyExistsDisciplineInStudentException.class)
    ResponseEntity<MessageDTO> handleAlreadyExistsBookInPersonExceptionException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Add imposible. The student already study this discipline"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DisciplineAbsentException.class)
    ResponseEntity<MessageDTO> handleBookAbsentException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Now this discipline is absent"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentHasNotDisciplineException.class)
    ResponseEntity<MessageDTO> handlePersonHasNotBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("The student don't study this discipline"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchLogException.class)
    ResponseEntity<MessageDTO> handleNoSuchLogException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such log not found"), HttpStatus.NOT_FOUND);
    }

}
