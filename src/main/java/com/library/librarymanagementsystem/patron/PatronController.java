package com.library.librarymanagementsystem.patron;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    private final PatronRepository patronRepository;

    public PatronController(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Patron> findAll(){
        return patronRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<Patron> findById(@PathVariable Integer id)
    {
        return patronRepository.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void Create(@RequestBody Patron patron)
    {
        patronRepository.save(patron);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patron update(@Valid @RequestBody Patron patron, @PathVariable Integer id)
    {
        return  patronRepository.save(patron);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id)
    {
        patronRepository.deleteById(id);
    }
}
