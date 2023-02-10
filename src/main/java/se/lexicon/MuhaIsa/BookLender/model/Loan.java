package se.lexicon.MuhaIsa.BookLender.model;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;


public class Loan {
    private long loanId;
    private LibraryUser loanTaker;
    private Book book;
    private LocalDate loanDate;
    private boolean concluded;

    public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean concluded) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.concluded = concluded;
    }

    public long getLoanId() {
        return loanId;
    }

    public LibraryUser getLoanTaker() {
        return loanTaker;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public boolean isConcluded() {
        return concluded;
    }

    public void setLoanTaker(LibraryUser loanTaker) {
        this.loanTaker = loanTaker;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isOverdue(){
        return LocalDate.now().isAfter(loanDate.plusDays(book.getMaxLoanDays()));
    }

    public BigDecimal getFine(){
        LocalDate start = loanDate;
        LocalDate end = LocalDate.now();
        Duration duration = Duration.between(start, end);
        long daysOverdue = duration.toDays();
        if(daysOverdue <= 0) return BigDecimal.ZERO;
        return book.getFinePerDay().multiply(BigDecimal.valueOf(daysOverdue));
    }

    public boolean extendLoan(int days){
    if(book.isReserved()) return false;
        LocalDate newLoanDate = loanDate.plusDays(days);
        loanDate = newLoanDate;
        return true;
    }
    public void setConcluded(boolean concluded) {
        this.concluded = concluded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return loanId == loan.loanId && concluded == loan.concluded && Objects.equals(loanTaker, loan.loanTaker) && Objects.equals(book, loan.book) && Objects.equals(loanDate, loan.loanDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, loanTaker, book, loanDate, concluded);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", loanTaker=" + loanTaker +
                ", book=" + book +
                ", loanDate=" + loanDate +
                ", concluded=" + concluded +
                '}';
    }
}
