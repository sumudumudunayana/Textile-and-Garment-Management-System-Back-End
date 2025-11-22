package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReadyMadeOrderItem {
    private Integer productId;
    private String itemName;
    private Double itemPrice;
    private Integer quantity;
    private Double lineTotal;

}
