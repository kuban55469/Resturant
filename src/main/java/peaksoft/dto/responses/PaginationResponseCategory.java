package peaksoft.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 27.03.2023
 */
@Getter @Setter
public class PaginationResponseCategory {
    private List<CategoryResponse> categoryResponses;
    private int currentPage;
    private int pageSize;
}
