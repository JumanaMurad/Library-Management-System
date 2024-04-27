package com.library.librarymanagementsystem.borrowRecord;

import com.library.librarymanagementsystem.book.Book;
import com.library.librarymanagementsystem.book.BookRepository;
import com.library.librarymanagementsystem.borrowRecord.mapper.BorrowRecordMapper;
import com.library.librarymanagementsystem.patron.Patron;
import com.library.librarymanagementsystem.patron.PatronRepository;
import com.library.librarymanagementsystem.borrowRecord.dto.BorrowRecordDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<BorrowRecordDto> findAll()
    {
        List<BorrowRecord> borrowRecords = borrowRecordRepository.findAll();
        return borrowRecordMapper.convertToDtoList(borrowRecords);
    }

    @Transactional
    public void borrowBook(Integer bookId, Integer patronId, BorrowRecord borrowRecord)
    {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + bookId));

        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new IllegalArgumentException("Patron not found with ID: " + patronId));

        BorrowRecord newBorrowRecord = new BorrowRecord();
        newBorrowRecord.setBorrowDate(borrowRecord.getBorrowDate());
        newBorrowRecord.setDueDate(borrowRecord.getDueDate());
        newBorrowRecord.setReturnDate(null);
        newBorrowRecord.setBook(book);
        newBorrowRecord.setPatron(patron);

        borrowRecordRepository.save(newBorrowRecord);
    }

    @Transactional
    public BorrowRecordDto returnBook(Integer bookId, Integer patronId, BorrowRecord borrowRecord)
    {
        BorrowRecord existingBorrowRecord = borrowRecordRepository.findByBookIdAndPatronId(bookId, patronId);

        existingBorrowRecord.setReturnDate(borrowRecord.getReturnDate());
        BorrowRecord updatedRecord = borrowRecordRepository.save(existingBorrowRecord);

        return borrowRecordMapper.convertToDto(updatedRecord);
    }

}

