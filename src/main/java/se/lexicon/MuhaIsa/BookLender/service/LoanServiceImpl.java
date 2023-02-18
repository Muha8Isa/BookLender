package se.lexicon.MuhaIsa.BookLender.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.MuhaIsa.BookLender.Exceptions.DataDuplicateException;
import se.lexicon.MuhaIsa.BookLender.Exceptions.DataNotFoundException;
import se.lexicon.MuhaIsa.BookLender.Repository.BookRepository;
import se.lexicon.MuhaIsa.BookLender.Repository.LoanRepository;
import se.lexicon.MuhaIsa.BookLender.Repository.UserRepository;
import se.lexicon.MuhaIsa.BookLender.dto.BookDto;
import se.lexicon.MuhaIsa.BookLender.dto.LoanDto;
import se.lexicon.MuhaIsa.BookLender.model.Book;
import se.lexicon.MuhaIsa.BookLender.model.Loan;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService{

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    ModelMapper modelMapper; //used to convert to and from DTO. It has dependency inside pom file.
    @Autowired
    private UserRepository userRepository;

    @Override
    public LoanDto findById(Long loanId) {
        if(loanId == null) throw new IllegalArgumentException("loan id was null");
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        if(!optionalLoan.isPresent()) throw new DataNotFoundException("book id was not valid!");
        Loan entity = optionalLoan.get();
        return modelMapper.map(entity, LoanDto.class); // Converts (from, to)
    }

    @Override
    public List<LoanDto> findByBookId(String bookId) {
        if(bookId == null) throw new IllegalArgumentException("book id was null");
        List<Loan> loanList = loanRepository.findAllByBookBookId(bookId);
        if(loanList.isEmpty()) throw new DataNotFoundException("book was not valid!");
        return modelMapper.map(loanList, new TypeToken<List<LoanDto>>(){}.getType());
    }

    @Override
    public List<LoanDto> findByUserId(Integer userId) {
        if(userId == null) throw new IllegalArgumentException("user id was null");
        List<Loan> loanList = loanRepository.findAllByLoanTakerUserId(userId);
        if(loanList.isEmpty()) throw new DataNotFoundException("user was not valid!");
        return modelMapper.map(loanList, new TypeToken<List<LoanDto>>(){}.getType());
    }

    @Override
    public List<LoanDto> findByConcluded(Boolean concluded) {
        if(!concluded) throw new IllegalArgumentException("loan was concluded"); //IllegalArgumentException?
        List<Loan> loanList = loanRepository.findByConcluded(concluded);
        return modelMapper.map(loanList, new TypeToken<List<LoanDto>>(){}.getType());
    }

    @Override
    public List<LoanDto> findAll() {
        Iterable<Loan> loanList = loanRepository.findAll();
        return modelMapper.map(loanList, new TypeToken<List<LoanDto>>(){}.getType());
    }

    @Override
    public LoanDto create(LoanDto loanDto) {
        if(loanDto == null) throw new IllegalArgumentException("loan data was null");
        if(loanDto.getLoanId() != 0) throw new IllegalArgumentException("loan id should be null or zero");
        Loan createdEntity = loanRepository.save(modelMapper.map(loanDto, Loan.class));
        return modelMapper.map(createdEntity, LoanDto.class);
    }

    @Override
    public void update(LoanDto loanDto) {
        if(loanDto == null) throw new IllegalArgumentException("loan data was null");
        if(loanDto.getLoanId() == 0) throw new IllegalArgumentException("loan id should not be zero");
        if(!loanRepository.findById(loanDto.getLoanId()).isPresent()) throw new DataNotFoundException("data not found Error");
        if(loanRepository.findById(loanDto.getLoanId()).isPresent()) throw new DataDuplicateException("duplicate Error");
        loanRepository.save(modelMapper.map(loanDto, Loan.class));
    }

    @Override
    public void delete(long loanId) {
        LoanDto loanDto = findById(loanId);
        if(loanDto == null) throw new DataNotFoundException("data not found");
        loanRepository.deleteById(loanId);

    }
}
