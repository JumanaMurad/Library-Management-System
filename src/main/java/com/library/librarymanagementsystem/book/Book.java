package com.library.librarymanagementsystem.book;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.library.librarymanagementsystem.borrowRecord.BorrowRecord;
import jakarta.persistence.*;

import java.util.List;

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
    private Double rating;
    @Column(nullable = false)
    private Integer total_pages;

    @OneToMany(mappedBy = "book")
    @JsonManagedReference
    private List<BorrowRecord> borrowRecord;


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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublished_date() {
        return published_year;
    }

    public void setPublished_date(Integer published_year) {
        this.published_year = published_year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String genre) {
        this.category = category;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public List<BorrowRecord> getBorrowRecord() {
        return borrowRecord;
    }

    public void setBorrowRecord(List<BorrowRecord> borrowRecord) {
        this.borrowRecord = borrowRecord;
    }
}