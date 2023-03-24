package peaksoft.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.SubCategoryRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.SubCategoryResponse;
import peaksoft.services.SubCategoryService;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
@RestController
@RequestMapping("/api/subCategories")
public class SubCategoryApi {

    private final SubCategoryService subCategoryService;


    public SubCategoryApi(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @PostMapping("/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveSubCategory(@PathVariable Long categoryId,
                                          @RequestBody SubCategoryRequest subCategoryRequest){
        return subCategoryService.save(categoryId,subCategoryRequest);
    }

    @GetMapping("/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<SubCategoryResponse> findAllByCategory(@PathVariable Long categoryId){
        return subCategoryService.findAllByCategory(categoryId);
    }

    @GetMapping("/getById/{subId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SubCategoryResponse getById(@PathVariable Long subId){
        return subCategoryService.getById(subId);
    }

    @PutMapping("/{subId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse updateSub(@PathVariable Long subId,
                                    @RequestBody SubCategoryRequest subCategoryRequest){
        return subCategoryService.update(subId, subCategoryRequest);
    }

    @DeleteMapping("/{categoryId}/{subCategoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteSub(@PathVariable Long categoryId,
                                    @PathVariable Long subCategoryId){
        return subCategoryService.deleteSub(categoryId, subCategoryId);
    }


}
