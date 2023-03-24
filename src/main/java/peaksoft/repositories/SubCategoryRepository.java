package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.responses.SubCategoryResponse;
import peaksoft.entity.SubCategory;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    @Query("select new peaksoft.dto.responses.SubCategoryResponse(s.id, s.name) from SubCategory s where s.category.id=?1 order by s.name")
    List<SubCategoryResponse> findAllSubCategories(Long categoryId);

    @Query("select new peaksoft.dto.responses.SubCategoryResponse(s.id, s.name) from SubCategory s where s.id=?1")
    Optional<SubCategoryResponse> findByIdSub(Long subId);


    @Query("select new peaksoft.dto.responses.SubCategoryResponse(s.id, s.name) from SubCategory s where s.category.id=?1")
    List<SubCategoryResponse> findAllSubCategoriesByCategory(Long categoryId);



//    @Query("delete from SubCategory s where s.id =?1")
//    void deleteSubCategoryById(Long subCategoryId);

}