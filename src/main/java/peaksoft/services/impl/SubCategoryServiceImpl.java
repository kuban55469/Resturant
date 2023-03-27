package peaksoft.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.SubCategoryRequest;
import peaksoft.dto.responses.PaginationResponseSubCategory;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.SubCategoryResponse;
import peaksoft.entity.Category;
import peaksoft.entity.SubCategory;
import peaksoft.exeption.NotFoundException;
import peaksoft.repositories.CategoryRepository;
import peaksoft.repositories.SubCategoryRepository;
import peaksoft.services.SubCategoryService;

import java.util.List;

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
        if (!categoryRepository.existsById(categoryId)){
            throw new NotFoundException(String.format("Category with id: %d doesn't exist", categoryId));
        }

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(
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
    public List<SubCategoryResponse> findAllByCategory(Long categoryId) {
        return subCategoryRepository.findAllSubCategories(categoryId);
    }

    @Override
    public SubCategoryResponse getById(Long subId) {
        if (!subCategoryRepository.existsById(subId)){
            throw new NotFoundException(String.format("Sub Category with id: %d doesn't exist", subId));
        }
        return subCategoryRepository.findByIdSub(subId).orElseThrow(() -> new NotFoundException(
                String.format("Sub Category with id: %d doesn't exist", subId)
        ));
    }

    @Override
    public SimpleResponse update(Long subId, SubCategoryRequest request) {
        if (!subCategoryRepository.existsById(subId)){
            throw new NotFoundException(String.format("Sub Category with id: %d doesn't exist", subId));
        }
        SubCategory subCategory = subCategoryRepository.findById(subId).orElseThrow(() -> new NotFoundException(
                String.format("Sub category with Id: %d doesn't exist", subId)
        ));

        subCategory.setName(request.name());
        subCategoryRepository.save(subCategory);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Sub Category with id: %d successfully UPDATED", subId)).build();
    }

    @Override
    public SimpleResponse deleteSub(Long categoryId, Long subCategoryId) {
        if (!categoryRepository.existsById(categoryId)){
            throw new NotFoundException(String.format("Category with id: %d doesn't exist", categoryId));
        }
        if (!subCategoryRepository.existsById(subCategoryId)){
            throw new NotFoundException(String.format("Sub Category with id: %d doesn't exist", subCategoryId));
        }

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(
                String.format("Category with id: %d doesn't exist", categoryId)
        ));


        category.getSubCategories().removeIf(c->c.getId().equals(subCategoryId));



        subCategoryRepository.deleteById(subCategoryId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Sub category with id: %d successfully DELETED", subCategoryId)).build();
    }

    @Override
    public PaginationResponseSubCategory getSubCategoryPage(int page, int size, Long categoryId) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("name"));
        Page<SubCategoryResponse> subCategoryResponses = subCategoryRepository.findAllByCategory_Id(categoryId, pageable);

        PaginationResponseSubCategory responseSubCategory = new PaginationResponseSubCategory();
        responseSubCategory.setSubCategoryResponses(subCategoryResponses.getContent());
        responseSubCategory.setCurrentPage(pageable.getPageNumber()+1);
        responseSubCategory.setPageSize(subCategoryResponses.getTotalPages());

        return responseSubCategory;
    }

}

