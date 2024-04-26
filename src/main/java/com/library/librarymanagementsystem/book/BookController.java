package com.library.librarymanagementsystem.book;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Book> findAll()
    {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<Book> findById(@PathVariable Integer id)
    {
        return bookRepository.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void Create(@RequestBody Book book)
    {
        bookRepository.save(book);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book update(@Valid @RequestBody Book book, @PathVariable Integer id)
    {
        return  bookRepository.save(book);
    }

    @GetMapping("/search/{author}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Book> findByAuthor(@PathVariable String author)
    {
        return bookRepository.findAllByAuthorContaining(author);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id)
    {
        bookRepository.deleteById(id);
    }

}
