package peaksoft.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 18.03.2023
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryGroupSubResponse {
    private Long id;
    private String name;
    private List<SubCategoryResponse> subCategories;

    public CategoryGroupSubResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
