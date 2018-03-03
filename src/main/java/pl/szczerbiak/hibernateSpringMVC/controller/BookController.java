package pl.szczerbiak.hibernateSpringMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.szczerbiak.hibernateSpringMVC.model.Book;
import pl.szczerbiak.hibernateSpringMVC.repository.AuthorRepository;
import pl.szczerbiak.hibernateSpringMVC.repository.BookRepository;

import javax.validation.Valid;

@Controller
public class BookController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public String show(ModelMap modelMap) {
        modelMap.addAttribute("books", bookRepository.findAll());
        return "book/list";
    }

    @GetMapping("/books/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("book", new Book());
        modelMap.addAttribute("authors", authorRepository.findAll());
        return "/book/add";
    }

    @PostMapping("/books/add")
    public String save(ModelMap modelMap, @ModelAttribute @Valid Book book, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("authors", authorRepository.findAll());
            return "/book/add";
        }
        bookRepository.save(book);
        redirectAttributes.addFlashAttribute("message", "Book saved!");
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message","Book deleted!");
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String edit(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("book",bookRepository.findById(id).get());
        modelMap.addAttribute("authors", authorRepository.findAll());
        return "/book/add";
    }
}
