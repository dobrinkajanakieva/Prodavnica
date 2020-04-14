package Application.Repositories;

import Application.Models.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CategoryRepository implements ICategoryRepository {
    private HashMap<Long, Category> categories;
    private AtomicLong counter;

    public CategoryRepository() {
        this.categories = new HashMap<>();
        this.counter = new AtomicLong(0);
        Category c1 = new Category(1L, "Thriller/Mystery", "Thrilling and mysterious books.");
        Category c2 = new Category(2L, "Biography", "Biographies.");
        Category c3 = new Category(3L, "Drama", "Dramatic books.");
        Category c4 = new Category(4L, "Adventure", "Adventurous books.");
        this.categories.put(c1.getId(), c1);
        this.categories.put(c2.getId(), c2);
        this.categories.put(c3.getId(), c3);
        this.categories.put(c4.getId(), c4);
    }

    public List<Category> findAll() {
        return new ArrayList<Category>(this.categories.values());
    }

    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(this.categories.get(id));
    }

    public Category save(Category category) {
        if (category.getId() == null) {
            category.setId(this.counter.getAndIncrement());
        }
        this.categories.put(category.getId(), category);
        return category;
    }

    public void deleteById(Long id) {
        this.categories.remove(id);
    }

    public void deleteAll() {
        this.categories.clear();
    }
}
