package peaksoft.dto.responses;

import lombok.Builder;

import java.math.BigDecimal;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 27.03.2023
 */
@Builder
public record MenuItemResponseSearch(
        String categoryName,
        String subCategoryName,
        String menuItemName,
        String image,
        BigDecimal price
        ) {

}
