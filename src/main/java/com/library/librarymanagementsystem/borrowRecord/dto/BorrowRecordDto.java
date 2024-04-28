package com.library.librarymanagementsystem.borrowRecord.dto;

import java.util.Date;

public record BorrowRecordDto(
        Date borrowDate,
        Date returnDate,
        Integer patronId,
        Integer bookId

) {
}
