package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReadyMadeOrderCreate {
    private Integer customerId;
    private String customerName;
    private String date;
    private Double discountPercent;
    private Double discountAmount;
    private Double subtotal;
    private String pricingStrategy;


    private List<ReadyMadeOrderCreateItem> items;
}
