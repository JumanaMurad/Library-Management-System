package com.library.librarymanagementsystem.book;

import com.library.librarymanagementsystem.book.dto.BookDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {this.bookService = bookService;}

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Book> findAll()
    {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<Book> findById(@PathVariable Integer id)
    {
        return bookService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void Create(@RequestBody Book book)
    {
        bookService.create(book);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book update(@Valid @RequestBody Book book, @PathVariable Integer id)
    {
        return  bookService.update(book, id);
    }

    @GetMapping("/search/{author}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Book> findByAuthor(@PathVariable String author)
    {
        return bookService.findByAuthor(author);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id)
    {
        bookService.delete(id);
    }

}
