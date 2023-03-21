package peaksoft.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.CategoryRequest;
import peaksoft.dto.responses.CategoryGroupSubResponse;
import peaksoft.dto.responses.CategoryResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.SubCategoryResponse;
import peaksoft.entity.Category;
import peaksoft.exeption.NotFoundException;
import peaksoft.repositories.CategoryRepository;
import peaksoft.repositories.SubCategoryRepository;
import peaksoft.services.CategoryService;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
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
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException(String.format("Category with id: %d is not found", categoryId));
        }
        return categoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public SimpleResponse updateCategory(Long categoryId, CategoryRequest category) {
        Category category1 = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(
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
            throw new NotFoundException(String.format("Category with id: %d is not found", categoryId));
        }
        categoryRepository.deleteById(categoryId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Category with name: %d is successfully DELETED", categoryId))
                .build();
    }


    @Override
    public CategoryGroupSubResponse groupSupCategories(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException(String.format("Category with id: %d is not found", categoryId));
        }
        CategoryGroupSubResponse categoryGroupSubResponse = categoryRepository.findCategory(categoryId).orElseThrow();
        List<SubCategoryResponse> subCategoryResponses = subCategoryRepository.findAllSubCategoriesByCategory(categoryId);

        categoryGroupSubResponse.setSubCategories(subCategoryResponses);
        return categoryGroupSubResponse;
    }
}
