package se.lexicon.MuhaIsa.BookLender.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.MuhaIsa.BookLender.Exceptions.DataDuplicateException;
import se.lexicon.MuhaIsa.BookLender.Exceptions.DataNotFoundException;
import se.lexicon.MuhaIsa.BookLender.Repository.UserRepository;
import se.lexicon.MuhaIsa.BookLender.dto.LibraryUserDto;
import se.lexicon.MuhaIsa.BookLender.model.LibraryUser;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryUserServiceImpl implements LibraryUserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper; //used to convert to and from DTO. It has dependency inside pom file.

    @Override
    public LibraryUserDto findById(Integer userId) {
        if(userId == null) throw new IllegalArgumentException("user id was null");
        Optional<LibraryUser> optionalLibraryUser = userRepository.findById(userId);
        if(!optionalLibraryUser.isPresent()) throw new DataNotFoundException("user id was not valid!");
        LibraryUser entity = optionalLibraryUser.get();
        return modelMapper.map(entity, LibraryUserDto.class); // Converts (from, to)
    }

    @Override
    public LibraryUserDto findByEmail(String email) {
        if(email == null) throw new IllegalArgumentException("email was null");
        Optional<LibraryUser> optionalLibraryUser = userRepository.findByEmail(email);
        if(optionalLibraryUser.isEmpty()) throw new DataNotFoundException("email was not valid!");
        LibraryUser entity = optionalLibraryUser.get();
        return modelMapper.map(entity, LibraryUserDto.class); // Converts (from, to)
    }

    @Override
    public List<LibraryUserDto> findAll() {
        Iterable<LibraryUser> libraryUserDtoList = userRepository.findAll();
        return modelMapper.map(libraryUserDtoList, new TypeToken<List<LibraryUserDto>>(){}.getType());
    }

    @Override
    public LibraryUserDto create(LibraryUserDto libraryUserDto) {
        if(libraryUserDto == null) throw new IllegalArgumentException("user data was null");
        if(libraryUserDto.getUserId() != 0) throw new IllegalArgumentException("user id should be null or zero");
        LibraryUser createdEntity = userRepository.save(modelMapper.map(libraryUserDto, LibraryUser.class));
        return modelMapper.map(createdEntity, LibraryUserDto.class);
    }

    @Override
    public void update(LibraryUserDto libraryUserDto) {
        if(libraryUserDto == null) throw new IllegalArgumentException("user data was null");
        if(libraryUserDto.getUserId() == 0) throw new IllegalArgumentException("user id should not be zero");
        if(!userRepository.findById(libraryUserDto.getUserId()).isPresent()) throw new DataNotFoundException("data not found Error");
        if(userRepository.findByEmail(libraryUserDto.getEmail()).isPresent()) throw new DataDuplicateException("duplicate Error");
        userRepository.save(modelMapper.map(libraryUserDto, LibraryUser.class));
    }

    @Override
    public void delete(int userId) {
        LibraryUserDto libraryUserDto = findById(userId);
        if(libraryUserDto == null) throw new DataNotFoundException("data not found");
        userRepository.deleteById(userId);
    }
}
