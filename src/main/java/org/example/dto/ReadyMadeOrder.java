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
public class ReadyMadeOrder {
    private Integer readyMadeOrderId;
    private Integer customerId;
    private String customerName;
    private String date;

    private Double subtotal;
    private Double discountPercent;
    private Double discountAmount;
    private Double totalAmount;

    private List<ReadyMadeOrderItem> items;
}
