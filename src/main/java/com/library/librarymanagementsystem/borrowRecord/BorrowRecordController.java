package com.library.librarymanagementsystem.borrowRecord;

import com.library.librarymanagementsystem.borrowRecord.dto.BorrowRecordDto;
import com.library.librarymanagementsystem.borrowRecord.BorrowRecord;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BorrowRecordController {

    private final BorrowRecordService borrowRecordService;

    public BorrowRecordController(BorrowRecordService borrowRecordService) {
        this.borrowRecordService = borrowRecordService;}

    @PostMapping("/api/borrow/{bookId}/patron/{patronId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void borrowBook(@PathVariable Integer bookId, @PathVariable Integer patronId) {
        borrowRecordService.borrowBook(bookId, patronId);
    }

    @PutMapping("api/return/{bookId}/patron/{patronId}")
    @ResponseStatus(HttpStatus.OK)
    public BorrowRecordDto returnBook(@PathVariable Integer bookId, @PathVariable Integer patronId)
    {
        return  borrowRecordService.returnBook(bookId, patronId);
    }
}