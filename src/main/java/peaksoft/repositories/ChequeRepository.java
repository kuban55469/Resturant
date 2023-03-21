package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.responses.ChequeResponse;
import peaksoft.dto.responses.ManuResponse;
import peaksoft.entity.Cheque;

import java.util.List;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    @Query("select new peaksoft.dto.responses.ChequeResponse(c.id,c.user.firstName, c.user.lastName," +
           "c.user.restaurant.service) from Cheque c where c.user.id=?1")
    List<ChequeResponse> findAllCheques(Long waiterId);

    @Query("select new peaksoft.dto.responses.ManuResponse(m.id, m.name, m.image, m.price," +
           " m.description, m.isVegetarian) from MenuItem m where m.id=?1")
    List<ManuResponse> getByMenuItemCheques(Long id);

    @Query(nativeQuery = true, value = "select menu_items_id from cheques_menu_items where cheque_id = :id")
    List<Long> getMenuIds(Long id);
}