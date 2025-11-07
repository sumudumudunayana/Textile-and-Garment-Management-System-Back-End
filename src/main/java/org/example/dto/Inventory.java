package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Inventory {
    private Integer id;
    private String productName;
    private String productCategory;
    private Integer quantity;
    private Double price;
    private String productEntryDate;
}
