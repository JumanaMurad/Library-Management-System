package com.library.librarymanagementsystem.patron.dto;

import com.library.librarymanagementsystem.borrowRecord.BorrowRecord;
import com.library.librarymanagementsystem.borrowRecord.dto.BorrowRecordDto;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record PatronDto(
        String name,
        String email,
        String phone,
        String address,
        List<BorrowRecord> borrowRecords
) {
}
