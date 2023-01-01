package library.springweb.newb.models;

import javax.validation.constraints.*;

public class Book {
    private int id;

    @NotEmpty(message="Please, enter the name of the book")
    @Size(min=2, max=45, message = "The name of the book must contain from 2 to 45 letters")
    private String name;
    @Min(value=1800, message="It's a library, not a museum!")
    @Max(value=2022, message = "We can't have any books from the future")
    private int year;
    @NotEmpty(message = "Every book must have it's author!")
    @Size(max=50, message = "Try something simple. That should be less than 50 letters.")
    private String author;
    private int person_id;


    public Book(int id, String name, String author, int year, int person_id) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.person_id = person_id;
    }
    public Book() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
