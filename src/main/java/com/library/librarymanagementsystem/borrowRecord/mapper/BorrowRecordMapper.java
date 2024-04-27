package com.library.librarymanagementsystem.borrowRecord.mapper;

import com.library.librarymanagementsystem.borrowRecord.BorrowRecord;
import com.library.librarymanagementsystem.borrowRecord.dto.BorrowRecordDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowRecordMapper
{
    //    Method to convert entity to DTO
    public BorrowRecordDto convertToDto(BorrowRecord borrowRecord)
    {
        return new com.library.librarymanagementsystem.borrowRecord.dto.BorrowRecordDto(
                borrowRecord.getId(),
                borrowRecord.getBorrowDate(),
                borrowRecord.getDueDate(),
                borrowRecord.getReturnDate(),
                borrowRecord.getPatron().getId(),
                borrowRecord.getBook().getId()
        );
    }

    //    Method to convert a list of entities to DTOs
    public List<BorrowRecordDto> convertToDtoList(List<BorrowRecord> borrowRecords) {
        return borrowRecords.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
