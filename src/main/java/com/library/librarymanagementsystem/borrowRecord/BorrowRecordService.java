package com.library.librarymanagementsystem.borrowRecord;

import com.library.librarymanagementsystem.book.Book;
import com.library.librarymanagementsystem.book.BookRepository;
import com.library.librarymanagementsystem.borrowRecord.mapper.BorrowRecordMapper;
import com.library.librarymanagementsystem.patron.Patron;
import com.library.librarymanagementsystem.patron.PatronRepository;
import com.library.librarymanagementsystem.borrowRecord.dto.BorrowRecordDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BorrowRecordService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final BorrowRecordMapper borrowRecordMapper;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository,
                               BookRepository bookRepository,
                               PatronRepository patronRepository,
                               BorrowRecordMapper borrowRecordMapper) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.borrowRecordMapper = borrowRecordMapper;
    }

    @Transactional
    public void borrowBook(Integer bookId, Integer patronId)
    {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + bookId));

        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new IllegalArgumentException("Patron not found with ID: " + patronId));

        BorrowRecord newBorrowRecord = new BorrowRecord();
        newBorrowRecord.setBorrowDate(new Date());
        newBorrowRecord.setReturnDate(null);
        newBorrowRecord.setBook(book);
        newBorrowRecord.setPatron(patron);

        borrowRecordRepository.save(newBorrowRecord);
    }

    @Transactional
    public BorrowRecordDto returnBook(Integer bookId, Integer patronId)
    {
        BorrowRecord existingBorrowRecord = borrowRecordRepository.findByBookIdAndPatronId(bookId, patronId);

        existingBorrowRecord.setReturnDate(new Date());
        BorrowRecord updatedRecord = borrowRecordRepository.save(existingBorrowRecord);

        return borrowRecordMapper.convertToDto(updatedRecord);
    }

}

