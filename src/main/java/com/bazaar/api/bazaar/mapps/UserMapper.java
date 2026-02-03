package com.bazaar.api.bazaar.mapps;


import org.mapstruct.*;

import com.bazaar.api.bazaar.dtos.UserRegistrationRequest;
import com.bazaar.api.bazaar.dtos.UserResponse;
import com.bazaar.api.bazaar.dtos.UserSummaryResponse;
import com.bazaar.api.bazaar.dtos.UserUpdateRequest;
import com.bazaar.api.bazaar.entities.User;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {
    
    // Registration Request to Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true) // Password akan di-hash terpisah
    @Mapping(target = "role", constant = "CUSTOMER")
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "emailVerified", constant = "false")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    User toEntity(UserRegistrationRequest request);
    
    // Entity to Response
    UserResponse toResponse(User user);
    
    // Entity to Summary Response
    UserSummaryResponse toSummaryResponse(User user);
    
    // List Entity to List Response
    List<UserResponse> toResponseList(List<User> users);
    
    // Update Entity from Request (hanya update field yang tidak null)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "emailVerified", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    void updateEntityFromRequest(UserUpdateRequest request, @MappingTarget User user);
}