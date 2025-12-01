package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table( name = "tailoring_order")
public class TailoringOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customerId;
    private String customerName;
    private String deliveryDate;
    private String fabricType;
    private Integer quantity;
    private Double totalAmount;
    private Double length;
    private Double width;
    private String description;
    private String status;
}
