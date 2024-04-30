package com.library.librarymanagementsystem.patron.mapper;

import com.library.librarymanagementsystem.patron.Patron;
import com.library.librarymanagementsystem.patron.dto.PatronDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatronMapper
{

    //    Method to convert DTO to Entity
    public Patron convertToPatron(PatronDto patronDto)
    {
        return new Patron(
                patronDto.name(),
                patronDto.email(),
                patronDto.phone(),
                patronDto.address(),
                patronDto.borrowRecords()
        );
    }

    //    Method to convert Entity to DTO
    public PatronDto convertToDto(Patron patron)
    {
        return new PatronDto(
                patron.getName(),
                patron.getEmail(),
                patron.getPhone(),
                patron.getAddress(),
                patron.getBorrowRecords()
        );
    }

    //    Method to convert a list of entities to DTOs
    public List<PatronDto> convertToDtoList(List<Patron> patrons) {
        return patrons.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
