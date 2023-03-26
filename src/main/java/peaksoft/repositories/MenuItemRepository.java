package peaksoft.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.responses.ManuResponse;
import peaksoft.entity.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("select new peaksoft.dto.responses.ManuResponse(m.id, m.name, m.image, m.price, m.description, m.isVegetarian)" +
           " from MenuItem m where m.restaurant.id=?1 and m.isVegetarian = ?2 order by m.price asc")
    List<ManuResponse> findAllMenusAsc(Long restId, Boolean i);

    @Query("select new peaksoft.dto.responses.ManuResponse(m.id, m.name, m.image, m.price, m.description, m.isVegetarian)" +
           " from MenuItem m where m.restaurant.id=?1 and m.isVegetarian=?2 order by m.price desc ")
    List<ManuResponse> findAllMenusDesc(Long restId, Boolean i);
    @Query("select new peaksoft.dto.responses.ManuResponse(m.id, m.name, m.image, m.price, m.description, m.isVegetarian)" +
           " from MenuItem m where m.id=?1")
    Optional<ManuResponse> findByMenuId(Long menuId);

    @Query("select new peaksoft.dto.responses.ManuResponse(m.id, m.name, m.image, m.price, m.description, m.isVegetarian)" +
           " from MenuItem m")
    List<ManuResponse> findAllMenusCheque();

    @Query("select new peaksoft.dto.responses.ManuResponse(m.id, m.name, m.image, m.price, m.description, m.isVegetarian)" +
           " from MenuItem m where m.id=?1")
    Optional<ManuResponse> findByIdMenuId(Long id);



    Page<ManuResponse> findAllBySubCategory_Id(Long subId, Pageable pageable);
    List<ManuResponse> findAllBySubCategory_Id(Long subId, Sort sort);
}