package peaksoft.services;

import peaksoft.dto.requests.SubCategoryRequest;
import peaksoft.dto.responses.PaginationResponseSubCategory;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.SubCategoryResponse;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
public interface SubCategoryService {
    SimpleResponse save(Long categoryId, SubCategoryRequest subCategoryRequest);

    List<SubCategoryResponse> findAllByCategory(Long categoryId);

    SubCategoryResponse getById(Long subId);

    SimpleResponse update(Long subId, SubCategoryRequest request);

    SimpleResponse deleteSub(Long categoryId, Long subCategoryId);

    PaginationResponseSubCategory getSubCategoryPage(int page, int size, Long categoryId);

}
