package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TailoringOrder {
    private Integer id;
    private Integer customerId;
    private String customerName;
    private String deliveryDate;
    private Integer quantity;
    private String fabricType;
    private Double totalAmount;
    private Double length;
    private Double height;
    private Double width;
    private String description;
    private String status;
}

