package com.library.librarymanagementsystem.borrowRecord.dto;

import java.util.Date;

public record BorrowRecordDto(
        Integer id,
        Date borrowDate,
        Date dueDate,
        Date returnDate,
        Integer patronId,
        Integer bookId

) {
}
