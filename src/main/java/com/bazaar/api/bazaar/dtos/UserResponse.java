package com.bazaar.api.bazaar.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.bazaar.api.bazaar.entities.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    
    private Long id;
    private String email;
    private String username;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String profileImageUrl;
    private User.UserRole role;
    private User.UserStatus status;
    private Boolean emailVerified;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}