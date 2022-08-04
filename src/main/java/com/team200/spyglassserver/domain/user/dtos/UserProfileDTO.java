package com.team200.spyglassserver.domain.user.dtos;
import lombok.*;

@Getter
@ToString
public class UserProfileDTO {
    private String id;
    private String fullName;
    private Integer numberOfGoals;

    public UserProfileDTO(UserProfile userProfile){
        id = userProfile.getId();
        fullName = String.format("%s %s",userProfile.getFirstName(),userProfile.getLastName());
        numberOfGoals = userProfile.getGoals().size();
    }
}