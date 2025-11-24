package com.ecom.app.order.payload;

import com.ecom.app.order.models.UserRole;
import lombok.Data;

@Data
public class UserResponseDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDTO address;
}
