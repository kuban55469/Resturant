package peaksoft.services;

import peaksoft.dto.requests.WaiterRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.WaiterResponse;
import peaksoft.enums.Role;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.03.2023
 */
public interface WaiterService {
    SimpleResponse saveWaiter(Long restId, WaiterRequest waiter);

    List<WaiterResponse> findAllWaiters(Long restId, Role role);

    WaiterResponse findById(Long waiterId, Role role);

    SimpleResponse updateWaiter(Long waiterId, WaiterRequest waiter);

    SimpleResponse deleteWaiter(Long restId, Long waiterId);
}
