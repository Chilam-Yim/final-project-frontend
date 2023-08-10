package FinalProject_frontend.model.dto;

import FinalProject_frontend.model.Item.Brand;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemsSearch {
    private int createYear;
    private Brand brandFrom;
}
