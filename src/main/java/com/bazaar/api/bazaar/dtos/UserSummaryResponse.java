package com.bazaar.api.bazaar.dtos;


import com.bazaar.api.bazaar.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryResponse {
    
    private Long id;
    private String username;
    private String fullName;
    private String profileImageUrl;
    private User.UserRole role;
}
