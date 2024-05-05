package com.library.librarymanagementsystem.book.mapper;

import com.library.librarymanagementsystem.book.Book;
import com.library.librarymanagementsystem.book.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper
{
    //    Method to convert entity to DTO
    public BookDto convertToDto(Book book)
    {
        return new BookDto(
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublished_year(),
                book.getCategory(),
                book.getRating(),
                book.getTotal_pages()

        );
    }

    //    Method to convert a list of entities to DTOs
    public List<BookDto> convertToDtoList(List<Book> books) {
        return books.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
