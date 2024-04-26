package com.library.librarymanagementsystem.borrowRecord;

import com.library.librarymanagementsystem.borrowRecord.dto.BorrowRecordDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrow-records")
public class BorrowRecordController {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BorrowRecordService borrowRecordService;

    public BorrowRecordController(BorrowRecordRepository borrowRecordRepository, BorrowRecordService borrowRecordService)
    {
        this.borrowRecordRepository = borrowRecordRepository;
        this.borrowRecordService = borrowRecordService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<BorrowRecord> findAll()
    {
        return borrowRecordRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<BorrowRecord> findById(@PathVariable Integer id)
    {
        return borrowRecordRepository.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void Create(@Valid @RequestBody BorrowRecordDto borrowRecordDto) {
        borrowRecordService.createBorrowRecord(borrowRecordDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BorrowRecord update(@Valid @RequestBody BorrowRecord borrowRecord, @PathVariable Integer id)
    {
        return  borrowRecordRepository.save(borrowRecord);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id)
    {
        borrowRecordRepository.deleteById(id);
    }
}