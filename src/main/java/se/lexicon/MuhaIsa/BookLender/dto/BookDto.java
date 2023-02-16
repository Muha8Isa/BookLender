package se.lexicon.MuhaIsa.BookLender.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {
    private int bookId;
    private String title;
    private boolean available;
    private boolean reserved;
    private int maxLoanDate;
    private BigDecimal finePerDay;
    private String description;
}
