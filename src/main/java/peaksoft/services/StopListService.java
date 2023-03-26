package peaksoft.services;

import peaksoft.dto.requests.StopListRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.StopListResponse;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.03.2023
 */
public interface StopListService {

    SimpleResponse saveStopList(StopListRequest request, Long menuItemId);

    List<StopListResponse> findAll();


    StopListResponse getById(Long id);

    SimpleResponse update(Long id, StopListRequest request);

    SimpleResponse delete(Long menuId ,Long stopListId);

}
