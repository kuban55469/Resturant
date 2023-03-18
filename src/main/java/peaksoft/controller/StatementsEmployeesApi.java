package peaksoft.controller;

import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.StatementRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.StatementResponse;
import peaksoft.enums.Role;
import peaksoft.services.StatementService;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */
@RestController
@RequestMapping("/api/statements")
public class StatementsEmployeesApi {

    private final StatementService statementService;

    public StatementsEmployeesApi(StatementService statementService) {
        this.statementService = statementService;
    }

    @PostMapping
    public SimpleResponse statement(@RequestBody StatementRequest request){
        return statementService.saveStatement(request);
    }

    @GetMapping("/findAll")
    public List<StatementResponse> findAllStatements(){
        return statementService.findAll();
    }


    @PostMapping("/{restId}/{newStateId}")
    public SimpleResponse acceptOrDelete(@PathVariable Long restId,
                                         @PathVariable Long newStateId,
                                         @RequestParam Boolean acceptOrDel){
        return statementService.acceptOrDelete(restId, newStateId, acceptOrDel);
    }
}
