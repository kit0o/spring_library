package library.springweb.newb.dao;

import library.springweb.newb.models.Book;
import library.springweb.newb.models.Person;
import library.springweb.newb.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //ниже для запроса нескольких столбцов бд и их сопоставления с сущностью мы используем BeanPropertyRowMapper
    //с указанием класса
    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, birthdate) VALUES(?,?)",
                person.getName(), person.getBirthdate());
    }
    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, birthdate=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getBirthdate(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
    public List<Book> showBookList(int id){
        return jdbcTemplate.query("SELECT * FROM Book where person_id =? ",
                new BeanPropertyRowMapper<>(Book.class), id);
    }
}
