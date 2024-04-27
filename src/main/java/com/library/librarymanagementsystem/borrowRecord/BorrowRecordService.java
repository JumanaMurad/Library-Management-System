package com.library.librarymanagementsystem.borrowRecord;

import com.library.librarymanagementsystem.book.Book;
import com.library.librarymanagementsystem.book.BookRepository;
import com.library.librarymanagementsystem.borrowRecord.mapper.BorrowRecordMapper;
import com.library.librarymanagementsystem.patron.Patron;
import com.library.librarymanagementsystem.patron.PatronRepository;
import com.library.librarymanagementsystem.borrowRecord.dto.BorrowRecordDto;
import com.library.librarymanagementsystem.borrowRecord.BorrowRecord;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public BorrowRecordDto findById(Integer id)
    {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Borrow Record not found with ID: " + id));
        return borrowRecordMapper.convertToDto(borrowRecord);
    }

    @Transactional
    public void createBorrowRecord(BorrowRecord borrowRecord)
    {
        Book book = bookRepository.findById(borrowRecord.getBook().getId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + borrowRecord.getBook().getId()));

        Patron patron = patronRepository.findById(borrowRecord.getPatron().getId())
                .orElseThrow(() -> new IllegalArgumentException("Patron not found with ID: " + borrowRecord.getPatron().getId()));

        BorrowRecord newBorrowRecord = new BorrowRecord();
        newBorrowRecord.setBorrowDate(borrowRecord.getBorrowDate());
        newBorrowRecord.setDueDate(borrowRecord.getDueDate());
        newBorrowRecord.setReturnDate(null);
        newBorrowRecord.setBook(book);
        newBorrowRecord.setPatron(patron);

        borrowRecordRepository.save(newBorrowRecord);
    }

    @Transactional
    public BorrowRecordDto updateBorrowRecord(BorrowRecord borrowRecord, Integer id)
    {
        BorrowRecord existingRecord = borrowRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Borrow Record not found with ID: " + id));

        Date returnDate = borrowRecord.getReturnDate();
        existingRecord.setReturnDate(returnDate);
        BorrowRecord updatedRecord = borrowRecordRepository.save(existingRecord);

        return borrowRecordMapper.convertToDto(updatedRecord);
    }

    @Transactional
    public void deleteBorrowRecord(Integer id) {
        borrowRecordRepository.deleteById(id);
    }

}

