package peaksoft.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.responses.ChequeResponse;
import peaksoft.dto.responses.ManuResponse;
import peaksoft.entity.Cheque;

import java.util.List;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {

    @Query(nativeQuery = true, value = "select menu_items_id from cheques_menu_items where cheque_id = :id")
    List<Long> getMenuIds(Long id);

    @Query("select new peaksoft.dto.responses.ChequeResponse(c.id, c.user.firstName, c.user.lastName, c.user.restaurant.service) from Cheque c where c.user.id = ?1")
    List<ChequeResponse> findAllByWaiterId(Long waiterID);

}