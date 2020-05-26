package Application.Services;

import Application.Models.Category;
import Application.Models.Exceptions.CategoryNotFoundException;
//import Application.Repositories.CategoryRepository;
import Application.Repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    //public CategoryService(CategoryRepository categoryRepository) {
        //this.categoryRepository = categoryRepository;
    //}

    public CategoryService() {}

    public List<Category> findAll() {
        List<Category> result = new ArrayList<Category>();
        this.categoryRepository.findAll().forEach(result::add);
        return result;
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
