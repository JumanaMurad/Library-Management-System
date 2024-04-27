package com.library.librarymanagementsystem.borrowRecord;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.librarymanagementsystem.book.Book;
import com.library.librarymanagementsystem.patron.Patron;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

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
    @Column(name = "due_date")
    private Date dueDate;
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

    public BorrowRecord(Date borrowDate, Date dueDate, Date returnDate, Book book, Patron patron) {
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.book = book;
        this.patron = patron;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
