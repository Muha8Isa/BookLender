package se.lexicon.MuhaIsa.BookLender.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {
    private int bookId;
    private String title;
    private boolean available;
    private boolean reserved;
    private int maxLoanDays;
    private BigDecimal finePerDay;
    private String description;

    public Book(String title, int maxLoanDays, BigDecimal finePerDay, String description) {
        this.title = title;
        this.maxLoanDays = maxLoanDays;
        this.finePerDay = finePerDay;
        this.description = description;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isReserved() {
        return reserved;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public BigDecimal getFinePerDay() {
        return finePerDay;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    public void setFinePerDay(BigDecimal finePerDay) {
        this.finePerDay = finePerDay;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && available == book.available && reserved == book.reserved && maxLoanDays == book.maxLoanDays && Objects.equals(title, book.title) && Objects.equals(finePerDay, book.finePerDay) && Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, available, reserved, maxLoanDays, finePerDay, description);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", available=" + available +
                ", reserved=" + reserved +
                ", maxLoanDays=" + maxLoanDays +
                ", finePerDay=" + finePerDay +
                ", description='" + description + '\'' +
                '}';
    }
}
