package peaksoft.services;

import peaksoft.dto.requests.CategoryRequest;
import peaksoft.dto.responses.CategoryResponse;
import peaksoft.dto.responses.SimpleResponse;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
public interface CategoryService {
    SimpleResponse saveCategory(CategoryRequest category);

    List<CategoryResponse> findAllCategories();

    CategoryResponse findById(Long categoryId);

    SimpleResponse updateCategory(Long categoryId, CategoryRequest category);

    SimpleResponse delete(Long categoryId);

}
