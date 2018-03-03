package pl.szczerbiak.hibernateSpringMVC.repository;

import org.springframework.data.repository.CrudRepository;
import pl.szczerbiak.hibernateSpringMVC.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
