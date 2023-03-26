package peaksoft.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 27.03.2023
 */
@Getter @Setter
public class PaginationResponseSubCategory {
    private List<SubCategoryResponse> subCategoryResponses;
    private int currentPage;
    private int pageSize;
}
