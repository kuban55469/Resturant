package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.responses.CategoryGroupSubResponse;
import peaksoft.dto.responses.CategoryResponse;
import peaksoft.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select new peaksoft.dto.responses.CategoryResponse(c.id, c.name) from Category c")
    List<CategoryResponse> findAllCategories();

    @Query("select new peaksoft.dto.responses.CategoryResponse(c.id, c.name) from Category c where c.id=?1")
    CategoryResponse findByCategoryId(Long categoryId);

    @Query("select new peaksoft.dto.responses.CategoryGroupSubResponse(c.id, c.name) from Category c where c.id=?1")
    Optional<CategoryGroupSubResponse> findCategory(Long categoryId);
}