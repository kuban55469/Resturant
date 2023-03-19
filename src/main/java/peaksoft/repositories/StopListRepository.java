package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.responses.StopListResponse;
import peaksoft.entity.StopList;

import java.util.List;

public interface StopListRepository extends JpaRepository<StopList, Long> {

    @Query("select new peaksoft.dto.responses.StopListResponse(s.id, s.menuItem.name, s.reason, s.date) from StopList s")
    List<StopListResponse> findAllStopLists();

    @Query("select new peaksoft.dto.responses.StopListResponse(s.id, s.menuItem.name, s.reason, s.date) from StopList s where s.id=?1")
    StopListResponse findByIdStopList(Long id);
}