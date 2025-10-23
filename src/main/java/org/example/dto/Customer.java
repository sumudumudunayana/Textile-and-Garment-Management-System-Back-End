package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private Integer id;
    private String customerFirstName;
    private String customerLastName;
    private String customerAddress;
    private String customerEmail;
    private String customerPhoneNumber;
}
