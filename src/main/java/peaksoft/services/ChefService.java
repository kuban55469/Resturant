package peaksoft.services;


import peaksoft.dto.requests.ChefRequest;
import peaksoft.dto.responses.ChefResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.enums.Role;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.03.2023
 */
public interface ChefService {
    SimpleResponse saveCook(Long restId, ChefRequest chefRequest);

    List<ChefResponse> findAllChefs(Long restId, Role role);

    ChefResponse findById(Long chefId, Role role);

    SimpleResponse updateChefById(Long chefId, ChefRequest chefRequest);

    SimpleResponse deleteChef(Long restId, Long chefId);

}
