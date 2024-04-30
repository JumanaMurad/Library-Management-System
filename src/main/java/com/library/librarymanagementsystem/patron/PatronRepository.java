package com.library.librarymanagementsystem.patron;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatronRepository extends JpaRepository<Patron, Integer>
{
    Optional<Patron> findByEmail(String email);
}
