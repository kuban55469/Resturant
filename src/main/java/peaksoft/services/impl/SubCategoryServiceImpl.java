package peaksoft.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.SubCategoryRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.SubCategoryResponse;
import peaksoft.entity.Category;
import peaksoft.entity.SubCategory;
import peaksoft.repositories.CategoryRepository;
import peaksoft.repositories.SubCategoryRepository;
import peaksoft.services.SubCategoryService;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;

    private final CategoryRepository categoryRepository;
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SimpleResponse save(Long categoryId, SubCategoryRequest request) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException(
                String.format("Category with id: %d doesn't exist", categoryId)));

        SubCategory subCategory = new SubCategory();
        subCategory.setName(request.name());

        category.addSubCategory(subCategory);
        subCategory.setCategory(category);

        subCategoryRepository.save(subCategory);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Sub Category with name: %s successfully SAVED!",request.name())).build();
    }

    @Override
    public List<SubCategoryResponse> findAll(Long categoryId) {
        return subCategoryRepository.findAllSubCategories(categoryId);
    }

    @Override
    public SubCategoryResponse getById(Long subId) {
        return subCategoryRepository.findByIdSub(subId).orElseThrow(() -> new NoSuchElementException(
                String.format("Sub Category with id: %d doesn't exist", subId)
        ));
    }

    @Override
    public SimpleResponse update(Long subId, SubCategoryRequest request) {
        SubCategory subCategory = subCategoryRepository.findById(subId).orElseThrow(() -> new NoSuchElementException(
                String.format("Sub category with Id: %d doesn't exist", subId)
        ));

        if (!subCategoryRepository.existsById(subId)){
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Sub Category with id:" + subId + " doesn't exist").build();
        }

        subCategory.setName(request.name());
        subCategoryRepository.save(subCategory);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Sub Category with id: %d successfully UPDATED", subId)).build();
    }

    @Override
    public SimpleResponse deleteSub(Long categoryId, Long subCategoryId) {
        if (!subCategoryRepository.existsById(subCategoryId)){
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("Sub category with id: %d doesn't exist", subCategoryId)).build();
        }

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException(
                String.format("Category with id: %d doesn't exist", categoryId)
        ));

        category.getSubCategories().removeIf(c->c.getId().equals(subCategoryId));

        subCategoryRepository.deleteById(subCategoryId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Sub category with id: %d successfully DELETED", subCategoryId)).build();
    }
}

