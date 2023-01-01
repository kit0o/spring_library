package library.springweb.newb.controllers;
import library.springweb.newb.dao.BookDAO;
import library.springweb.newb.dao.PersonDAO;
import library.springweb.newb.models.Book;
import library.springweb.newb.models.Person;
import library.springweb.newb.util.PersonValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonValidator  personValidator;
    private PersonDAO personDAO;
    private BookDAO bookDAO;

    public PeopleController(PersonValidator personValidator, PersonDAO personDAO, BookDAO bookDAO) {
        this.personValidator = personValidator;
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, Model model2,
                       @ModelAttribute("book") Book book, @ModelAttribute("person") Person person) {
        model.addAttribute(personDAO.show(id));
        model2.addAttribute("library", bookDAO.indexToList());
        personDAO.showBookList(id);
        return "people/show";
    }
  //  @GetMapping("/list/{id}")
  //  public String membersList(@PathVariable("id") int id, @ModelAttribute("book") Book book, @ModelAttribute("person") Person person){
  //
  //      return "people/show";
  //  }
    @GetMapping("/new")
    public String newMember(@ModelAttribute("person") Person person) {
        return "people/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.save(person);
    return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
    model.addAttribute("person", personDAO.show(id));
    return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
