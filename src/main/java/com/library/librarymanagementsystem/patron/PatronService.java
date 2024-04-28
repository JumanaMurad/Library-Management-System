package com.library.librarymanagementsystem.patron;

import com.library.librarymanagementsystem.patron.dto.PatronDto;
import com.library.librarymanagementsystem.patron.mapper.PatronMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new IllegalArgumentException("Borrow Record not found with ID: " + id));

        return patronMapper.convertToDto(patron);
    }

    public void create(PatronDto patronDto)
    {
        patronRepository.save(patronMapper.convertToPatron(patronDto));
    }

    public PatronDto update(PatronDto patronDto, Integer id)
    {
        Patron existingPatron = patronRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Borrow Record not found with ID: " + id));
        String name = patronDto.name();
        String email = patronDto.email();
        String phone = patronDto.phone();
        String address = patronDto.address();
        existingPatron.setName(name);
        existingPatron.setEmail(email);
        existingPatron.setPhone(phone);
        existingPatron.setAddress(address);

        Patron updatedPatron = patronRepository.save(existingPatron);

        return  patronMapper.convertToDto(updatedPatron);
    }

    public void delete(Integer id)
    {
        patronRepository.deleteById(id);
    }
}
