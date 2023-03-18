package peaksoft.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.CategoryRequest;
import peaksoft.dto.responses.CategoryResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.entity.Category;
import peaksoft.repositories.CategoryRepository;
import peaksoft.services.CategoryService;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SimpleResponse saveCategory(CategoryRequest c) {
        Category category = new Category();
        category.setName(c.name());

        categoryRepository.save(category);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Category with name: %s is successfully SAVED", c.name()))
                .build();
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        return categoryRepository.findAllCategories();
    }

    @Override
    public CategoryResponse findById(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public SimpleResponse updateCategory(Long categoryId, CategoryRequest category) {
        Category category1 = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException(
                String.format("Category with id: %d doesn't exist", categoryId)));

        category1.setName(category.name());

        categoryRepository.save(category1);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Category with name: %s is successfully UPDATED", category.name()))
                .build();
    }

    @Override
    public SimpleResponse delete(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("Category with id: %d is not found", categoryId)).build();
        }
        categoryRepository.deleteById(categoryId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Category with name: %d is successfully DELETED", categoryId))
                .build();
    }
}
