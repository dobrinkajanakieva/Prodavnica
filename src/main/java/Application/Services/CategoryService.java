package Application.Services;

import Application.Models.Category;
import Application.Models.Exceptions.CategoryNotFoundException;
import Application.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category save(Category manufacturer) {
        return this.categoryRepository.save(manufacturer);
    }

    public Category update(Long id, Category category) {
        Category c = this.findById(id);
        c.setName(category.getName());
        c.setDescription(category.getDescription());
        return this.categoryRepository.save(c);
    }

    public Category updateName(Long id, String name) {
        Category category = this.findById(id);
        category.setName(name);
        return this.categoryRepository.save(category);
    }

    public Category updateDescription(Long id, String description) {
        Category category = this.findById(id);
        category.setDescription(description);
        return this.categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        this.categoryRepository.deleteById(id);
    }
}
