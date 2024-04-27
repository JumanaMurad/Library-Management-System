package com.library.librarymanagementsystem.patron.dto;

import com.library.librarymanagementsystem.borrowRecord.dto.BorrowRecordDto;

import java.util.List;

public record PatronDto(
        String name,
        String email
//        List<BorrowRecordDto> borrowRecords
) {
}
