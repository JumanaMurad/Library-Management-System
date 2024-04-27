package com.library.librarymanagementsystem.patron;

import com.library.librarymanagementsystem.borrowRecord.BorrowRecord;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    private String address;
    @OneToMany(mappedBy = "patron")
//    @JsonManagedReference
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