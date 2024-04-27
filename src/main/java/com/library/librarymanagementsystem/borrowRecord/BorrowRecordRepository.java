package com.library.librarymanagementsystem.borrowRecord;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer>
{
    BorrowRecord findByBookIdAndPatronId(Integer bookId, Integer patronId);
}
