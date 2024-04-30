package com.library.librarymanagementsystem.patron;

import com.library.librarymanagementsystem.patron.dto.PatronDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<PatronDto> findAll(){
        return patronService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public PatronDto findById(@PathVariable Integer id)
    {
        return patronService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody PatronDto patronDto)
    {
        patronService.create(patronDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatronDto update(@Valid @RequestBody PatronDto patronDto, @PathVariable Integer id)
    {
        return  patronService.update(patronDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id)
    {
        patronService.delete(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handelMethodArgumentNotValidException(
            MethodArgumentNotValidException exp)
    {
        var errors = new HashMap<String, String>();

        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return  new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
