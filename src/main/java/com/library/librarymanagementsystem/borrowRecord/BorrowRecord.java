package com.library.librarymanagementsystem.borrowRecord;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.librarymanagementsystem.book.Book;
import com.library.librarymanagementsystem.patron.Patron;
import com.library.librarymanagementsystem.patron.dto.PatronDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "borrow_record")
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "borrow_date")
    private Date borrowDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "return_date")
    private Date returnDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patron_id")
    @JsonIgnore
    private Patron patron;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    @JsonBackReference
    private Book book;

    public BorrowRecord() {}

    public BorrowRecord(Date borrowDate, Date returnDate, Book book, Patron patron) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.book = book;
        this.patron = patron;
    }
}
