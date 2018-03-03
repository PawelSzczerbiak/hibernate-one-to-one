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
import pl.szczerbiak.hibernateSpringMVC.model.Author;
import pl.szczerbiak.hibernateSpringMVC.repository.AuthorRepository;

import javax.validation.Valid;

@Controller
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    public String show(ModelMap modelMap) {
        modelMap.addAttribute("authors", authorRepository.findAll());
        return "author/list";
    }

    @GetMapping("/authors/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("author", new Author());
        return "/author/add";
    }

    @PostMapping("/authors")
    public String save(@ModelAttribute @Valid Author author, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/author/add";
        }
        authorRepository.save(author);
        redirectAttributes.addFlashAttribute("message", "Author saved!");
        return "redirect:/authors";
    }

    @GetMapping("/authors/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        authorRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message","Author deleted!");
        return "redirect:/authors";
    }

    @GetMapping("/authors/edit/{id}")
    public String edit(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("author",authorRepository.findById(id).get());
        return "/author/add";
    }
}
