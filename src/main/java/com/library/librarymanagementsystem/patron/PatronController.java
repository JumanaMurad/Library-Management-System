package com.library.librarymanagementsystem.patron;

import com.library.librarymanagementsystem.patron.dto.PatronDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

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
    public void create(@RequestBody Patron patron)
    {
        patronService.create(patron);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatronDto update(@Valid @RequestBody Patron patron, @PathVariable Integer id)
    {
        return  patronService.update(patron, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id)
    {
        patronService.delete(id);
    }
}
