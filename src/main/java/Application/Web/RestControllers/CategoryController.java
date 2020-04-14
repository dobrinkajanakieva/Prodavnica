package Application.Web.RestControllers;

import Application.Models.Category;
import Application.Services.CategoryService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> findAll() {
        return this.categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id) {
        return this.categoryService.findById(id);
    }

    @PostMapping
    public Category save(@Valid Category category) {
        return this.categoryService.save(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @Valid Category category) {
        return this.categoryService.update(id, category);
    }

    @PatchMapping("/{id}")
    public Category updateName(@PathVariable Long id, @RequestParam String name) {
        return this.categoryService.updateName(id, name);
    }

//    @PatchMapping("/{id}")
//    public Category updateDescription(@PathVariable Long id, @RequestParam String description) {
//        return this.categoryService.updateDescription(id, description);
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.categoryService.deleteById(id);
    }
}
