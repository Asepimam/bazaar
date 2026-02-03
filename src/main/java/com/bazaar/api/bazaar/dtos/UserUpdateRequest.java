package com.bazaar.api.bazaar.dtos;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    
    @Size(max = 100, message = "Full name must not exceed 100 characters")
    private String fullName;
    
    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Invalid phone number")
    private String phoneNumber;
    
    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;
    
    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;
    
    @Size(max = 100, message = "Province must not exceed 100 characters")
    private String province;
    
    @Size(max = 10, message = "Postal code must not exceed 10 characters")
    private String postalCode;
}