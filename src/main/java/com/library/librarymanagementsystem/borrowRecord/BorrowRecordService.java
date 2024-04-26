package com.library.librarymanagementsystem.borrowRecord;

import com.library.librarymanagementsystem.book.Book;
import com.library.librarymanagementsystem.book.BookRepository;
import com.library.librarymanagementsystem.borrowRecord.dto.BorrowRecordDto;
import com.library.librarymanagementsystem.patron.Patron;
import com.library.librarymanagementsystem.patron.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowRecordService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository,
                               BookRepository bookRepository,
                               PatronRepository patronRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    public List<BorrowRecord> findAll() {
        return borrowRecordRepository.findAll();
    }

    public BorrowRecord findById(Integer id) {
        return borrowRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Borrow Record not found with ID: " + id));
    }

    @Transactional
    public void createBorrowRecord(BorrowRecordDto borrowRecordDto) {
        Book book = bookRepository.findById(borrowRecordDto.bookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + borrowRecordDto.bookId()));

        Patron patron = patronRepository.findById(borrowRecordDto.patronId())
                .orElseThrow(() -> new IllegalArgumentException("Patron not found with ID: " + borrowRecordDto.patronId()));

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBorrowDate(borrowRecordDto.borrowDate());
        borrowRecord.setDueDate(borrowRecordDto.dueDate());
        borrowRecord.setReturnDate(null);
        borrowRecord.setBook(book);
        borrowRecord.setPatron(patron);

        borrowRecordRepository.save(borrowRecord);
    }

    @Transactional
    public BorrowRecord updateBorrowRecord(Integer id, BorrowRecord borrowRecord) {
        BorrowRecord existingRecord = borrowRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Borrow Record not found with ID: " + id));

        existingRecord.setBorrowDate(borrowRecord.getBorrowDate());
        existingRecord.setDueDate(borrowRecord.getDueDate());
        existingRecord.setReturnDate(borrowRecord.getReturnDate());
        existingRecord.setBook(bookRepository.findById(borrowRecord.getBook().getId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + borrowRecord.getBook().getId())));
        existingRecord.setPatron(patronRepository.findById(borrowRecord.getPatron().getId())
                .orElseThrow(() -> new IllegalArgumentException("Patron not found with ID: " + borrowRecord.getPatron().getId())));

        return borrowRecordRepository.save(existingRecord);
    }

    @Transactional
    public void deleteBorrowRecord(Integer id) {
        borrowRecordRepository.deleteById(id);
    }
}

