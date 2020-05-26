package Application.Repositories;
import Application.Models.Book;
import Application.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface IBookRepository extends CrudRepository<Book, Long> {

//    List<Book> findAll();
//
//    List<Book> findAllById(Long categoryId);
//
//    Optional<Book> findById(Long id);
//
//    Book save(Book book);
//
//    void deleteById(Long id);
}

