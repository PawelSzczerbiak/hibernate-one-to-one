package pl.szczerbiak.hibernateSpringMVC.repository;

import org.springframework.data.repository.CrudRepository;
import pl.szczerbiak.hibernateSpringMVC.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
