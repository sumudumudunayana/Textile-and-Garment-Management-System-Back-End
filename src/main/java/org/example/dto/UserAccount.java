package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAccount {
    private Integer id;
    private String userName;
    private String userAddress;
    private String userEmail;
    private String userPhoneNumber;
    private String userDate;
    private String userRole;
    private String userLoginName;
    private String userLoginPassword;
}
