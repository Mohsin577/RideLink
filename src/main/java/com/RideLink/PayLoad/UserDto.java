package com.RideLink.PayLoad;

import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String name;

    private String userType;

    private String email;

    private String phone;

    private String password;
}
