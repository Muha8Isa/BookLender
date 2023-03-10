package se.lexicon.MuhaIsa.BookLender.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private LocalDate regDate;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;

    public LibraryUser() {
    }

    public LibraryUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public LibraryUser(LocalDate regDate, String name, String email) {
        this.regDate = regDate;
        this.name = name;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryUser that = (LibraryUser) o;
        return userId == that.userId && Objects.equals(regDate, that.regDate) && Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, regDate, name, email);
    }

    @Override
    public String toString() {
        return "LibraryUser{" +
                "userId=" + userId +
                ", regDate=" + regDate +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
