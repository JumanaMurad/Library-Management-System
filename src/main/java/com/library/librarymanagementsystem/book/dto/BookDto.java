package com.library.librarymanagementsystem.book.dto;

public record BookDto(
        String title,
        String author,
        String isbn,
        Integer published_year,
        String category,
        Double rating,
        Integer total_pages
) {}
