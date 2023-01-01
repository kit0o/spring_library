package library.springweb.newb.models;

import javax.validation.constraints.*;

public class Person {
    private int id;
    @Size(min=2, max=45, message = "Your name should be between 2 and 50 characters")
    @NotEmpty(message="Your name should not be empty!")
    private String name;

    @Min(value = 1901, message="That's impossible. Please, enter your real birth year.")
    @Max(value = 2020, message="That's impossible. Please, enter your real birth year.")
    private int birthdate;

    public Person(int id, String name, int birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }
    public Person() {

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

    public int getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(int birthdate) {
        this.birthdate = birthdate;
    }
}
