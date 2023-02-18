package se.lexicon.MuhaIsa.BookLender.service;

import se.lexicon.MuhaIsa.BookLender.dto.LoanDto;

import java.util.List;

public interface LoanService {
    LoanDto findById(Long loanId);
    List<LoanDto> findByBookId(String bookId);
    List<LoanDto> findByUserId(Integer userId);
    List<LoanDto> findByConcluded(Boolean concluded);
    List<LoanDto> findAll();
    LoanDto create(LoanDto loanDto);

    void update(LoanDto loanDto);

    void delete(long loanId);
}
