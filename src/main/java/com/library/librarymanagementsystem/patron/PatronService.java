package com.library.librarymanagementsystem.patron;

import com.library.librarymanagementsystem.patron.dto.PatronDto;
import com.library.librarymanagementsystem.patron.mapper.PatronMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService
{
    private final PatronRepository patronRepository;
    private final PatronMapper patronMapper;

    public PatronService(
            PatronRepository patronRepository,
            PatronMapper patronMapper)
    {
        this.patronRepository = patronRepository;
        this.patronMapper = patronMapper;
    }

    public List<PatronDto> findAll()
    {
        List<Patron> patrons = patronRepository.findAll();
        return patronMapper.convertToDtoList(patrons);
    }

    public PatronDto findById(Integer id)
    {
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Borrow Record not found with ID: " + id));;
        return patronMapper.convertToDto(patron);
    }

    public void create(Patron patron)
    {
        patronRepository.save(patron);
    }

    public PatronDto update(Patron patron, Integer id)
    {
        Patron existingPatron = patronRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Borrow Record not found with ID: " + id));
        String name = patron.getName();
        String email = patron.getEmail();
        existingPatron.setName(name);
        existingPatron.setEmail(email);

        Patron updatedPatron = patronRepository.save(existingPatron);

        return  patronMapper.convertToDto(updatedPatron);
    }

    public void delete(Integer id)
    {
        patronRepository.deleteById(id);
    }
}
