package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReadyMadeOrderCreateItem {
    private Integer productId;
    private String name;
    private Double unitPrice;
    private Integer qty;
}
