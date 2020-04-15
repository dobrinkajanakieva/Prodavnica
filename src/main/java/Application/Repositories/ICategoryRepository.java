package Application.Repositories;

import Application.Models.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(Category category);
    void deleteById(Long id);
    void deleteAll();
}

