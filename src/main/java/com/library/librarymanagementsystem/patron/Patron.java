package com.library.librarymanagementsystem.patron;

import com.library.librarymanagementsystem.borrowRecord.BorrowRecord;
import com.library.librarymanagementsystem.borrowRecord.dto.BorrowRecordDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Name field should not be empty!")
    @Column(length = 100)
    private String name;
    @NotNull(message = "Email field should not be empty!")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Please enter a valid email address!")
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    private String address;
    @OneToMany(mappedBy = "patron")
    private List<BorrowRecord> borrowRecords;

    public Patron() {}

    public Patron(String name, String email, String phone, String address, List<BorrowRecord> borrowRecord)
    {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.borrowRecords = borrowRecord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }

    public void setBorrowRecords(List<BorrowRecord> borrowRecord) {
        this.borrowRecords = borrowRecord;
    }
}