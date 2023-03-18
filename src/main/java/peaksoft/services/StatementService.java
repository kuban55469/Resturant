package peaksoft.services;

import peaksoft.dto.requests.StatementRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.StatementResponse;
import peaksoft.enums.Role;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
public interface StatementService {
    SimpleResponse saveStatement(StatementRequest request);

    List<StatementResponse> findAll();

    SimpleResponse acceptOrDelete(Long restId, Long newStateId, Boolean acceptOrDel);

}
