package library.springweb.newb.controllers;

import library.springweb.newb.dao.BookDAO;
import library.springweb.newb.dao.PersonDAO;
import library.springweb.newb.models.Book;
import library.springweb.newb.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {
    private BookDAO bookDAO;
    private PersonDAO personDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("library",bookDAO.index());
        return "book/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, Model model1, @ModelAttribute("person") Person person){
        model1.addAttribute("people", personDAO.index());
        model.addAttribute(bookDAO.show(id));
        return "book/show";
    }
    @PatchMapping("/appoint/{id}")
    public String bookToGive(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookDAO.assign(person, id);
        return "book/luckyPage";
    }
    @PatchMapping("/free/{id}")
    public String freeButton(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookDAO.freeBook(id);
        return "book/luckyFree";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "book/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "book/new";
        }
        bookDAO.save(book);
        return "redirect:/book";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "book/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id")int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "book/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/book";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/book";
    }
}
