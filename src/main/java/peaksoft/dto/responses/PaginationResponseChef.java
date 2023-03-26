package peaksoft.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 26.03.2023
 */
@Getter
@Setter
public class PaginationResponseChef {
    private List<ChefResponse> chefResponses;
    private int currentPage;
    private int pageSize;
}
