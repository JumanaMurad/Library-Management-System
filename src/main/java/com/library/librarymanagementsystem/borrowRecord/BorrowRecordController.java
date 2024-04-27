package com.library.librarymanagementsystem.borrowRecord;

import com.library.librarymanagementsystem.borrowRecord.dto.BorrowRecordDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow-records")
public class BorrowRecordController {

    private final BorrowRecordService borrowRecordService;

    public BorrowRecordController(BorrowRecordService borrowRecordService)
    {
        this.borrowRecordService = borrowRecordService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<BorrowRecordDto> findAll()
    {
        return borrowRecordService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public BorrowRecordDto findById(@PathVariable Integer id)
    {
        return borrowRecordService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void Create(@Valid @RequestBody BorrowRecordDto borrowRecordDto) {
        borrowRecordService.createBorrowRecord(borrowRecordDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BorrowRecordDto update(@Valid @RequestBody BorrowRecord borrowRecord, @PathVariable Integer id)
    {
        return  borrowRecordService.updateBorrowRecord(borrowRecord, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id)
    {
        borrowRecordService.deleteBorrowRecord(id);
    }
}