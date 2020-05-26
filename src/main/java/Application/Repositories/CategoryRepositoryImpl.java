//package Application.Repositories;
//
//import Application.Models.Category;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Optional;
//import java.util.concurrent.atomic.AtomicLong;
//
//@Repository
//public class CategoryRepositoryImpl implements ICategoryRepository {
//
//    public CategoryRepositoryImpl() { }
//
//    public List<Category> findAll() {
//        return new ArrayList<Category>(this.categories.values());
//    }
//
//    public Optional<Category> findById(Long id) {
//        return Optional.ofNullable(this.categories.get(id));
//    }
//
//    public Category save(Category category) {
//        if (category.getId() == null) {
//            category.setId(this.counter.getAndIncrement());
//        }
//        this.categories.put(category.getId(), category);
//        return category;
//    }
//
//    public void deleteById(Long id) {
//        this.categories.remove(id);
//    }
//
//    public void deleteAll() {
//        this.categories.clear();
//    }
//}
