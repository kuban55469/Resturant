package peaksoft.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.responses.ManuResponse;
import peaksoft.dto.responses.MenuItemResponseSearch;
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

    @Query("SELECT new peaksoft.dto.responses.MenuItemResponseSearch(c.name,s.name,m.name,m.image,m.price) " +
           "FROM MenuItem  m join  m.subCategory s join s.category c where " +
           "(m.name ILIKE %:keyWord% OR c.name ILIKE %:keyWord% OR s.name ILIKE %:keyWord%)")
    List<MenuItemResponseSearch> search(String keyWord);


    Page<ManuResponse> findAllBySubCategory_Id(Long subId, Pageable pageable);
}