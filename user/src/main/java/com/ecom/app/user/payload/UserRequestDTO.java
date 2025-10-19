package com.ecom.app.user.payload;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDTO address;
}
