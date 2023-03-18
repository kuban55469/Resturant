package peaksoft.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.CategoryRequest;
import peaksoft.dto.responses.CategoryResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.services.CategoryService;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryApi {
    private final CategoryService categoryService;

    public CategoryApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveCategory(@RequestBody CategoryRequest category){
        return categoryService.saveCategory(category);
    }


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CategoryResponse> findAllCategories(){
        return categoryService.findAllCategories();
    }

    @GetMapping("/getById/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoryResponse getById(@PathVariable Long categoryId){
        return categoryService.findById(categoryId);
    }

    @PutMapping("/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse update(@PathVariable Long categoryId,
                                 @RequestBody CategoryRequest category){
        return categoryService.updateCategory(categoryId, category);
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long categoryId){
        return categoryService.delete(categoryId);
    }
}
