package FinalProject_frontend.model.dto;

import FinalProject_frontend.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributionCenterDto {
    private String name;
    private double latitude;
    private double longitude;
    List<Item> items;
}
