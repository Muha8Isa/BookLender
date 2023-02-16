package se.lexicon.MuhaIsa.BookLender.service;

import se.lexicon.MuhaIsa.BookLender.dto.LibraryUserDto;

import java.util.List;

public interface LibraryUserService {
    LibraryUserDto findById(Integer userId);
    LibraryUserDto findByEmail(String email);
    List<LibraryUserDto> findAll();
    LibraryUserDto create(LibraryUserDto libraryUserDto);

    void update(LibraryUserDto libraryUserDtoDto);

    void delete(boolean userId);

}
