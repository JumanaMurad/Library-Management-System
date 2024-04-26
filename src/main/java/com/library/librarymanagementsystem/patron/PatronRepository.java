package com.library.librarymanagementsystem.patron;

import com.library.librarymanagementsystem.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatronRepository extends JpaRepository<Patron, Integer>
{

    List<Book> findAllByNameContaining(String name);
}
