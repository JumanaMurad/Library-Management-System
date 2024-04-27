package com.library.librarymanagementsystem.book;

import com.library.librarymanagementsystem.book.dto.BookDto;
import com.library.librarymanagementsystem.book.mapper.BookMapper;
import com.library.librarymanagementsystem.patron.Patron;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService
{
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepositoryr) {this.bookRepository = bookRepositoryr;}

    public List<Book> findAll() {return bookRepository.findAll();}

    public Optional<Book> findById(Integer id)
    {
        return bookRepository.findById(id);
    }

    public void create(Book book)
    {
        bookRepository.save(book);
    }

    public Book update(Book book, Integer id)
    {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Borrow Record not found with ID: " + id));

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setCategory(book.getCategory());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setPublished_year(book.getPublished_year());
        existingBook.setTotal_pages(book.getTotal_pages());

        return  bookRepository.save(existingBook);
    }

    public List<Book> findByAuthor(String author)
    {
        return bookRepository.findAllByAuthorContaining(author);
    }

    public void delete(Integer id)
    {
        bookRepository.deleteById(id);
    }

}
