package com.library.librarymanagementsystem.book;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.library.librarymanagementsystem.borrowRecord.BorrowRecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(nullable = false)
    private String title;
    private String author;
    @Column(unique = true, nullable = false)
    private String isbn;
    private Integer published_year;
    private String category;
    @DecimalMax(value = "5.0")
    @DecimalMin(value = "0.0")
    private Double rating;
    @Column(nullable = false)
    private Integer total_pages;

    @OneToMany(mappedBy = "book")
    @JsonManagedReference
    private List<BorrowRecord> borrowRecords;


    public Book() {}
    public Book(String title, String author, String isbn, Integer published_year, String category, Double rating,  Integer total_pages) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.published_year = published_year;
        this.category = category;
        this.rating = rating;
        this.total_pages = total_pages;
    }
}