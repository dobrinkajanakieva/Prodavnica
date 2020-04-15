package Application.Services;


import Application.Models.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category save(Category manufacturer);
    Category update(Long id, Category category);
    Category updateName(Long id, String name);
    Category updateDescription(Long id, String description);
    void deleteById(Long id);
}
