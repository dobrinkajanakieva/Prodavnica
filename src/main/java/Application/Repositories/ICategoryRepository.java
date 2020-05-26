package Application.Repositories;

import Application.Models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends CrudRepository<Category, Long> {
//    List<Category> findAll();
//    Optional<Category> findById(Long id);
//    Category save(Category category);
//    void deleteById(Long id);
//    void deleteAll();
}

