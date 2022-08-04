package com.team200.spyglassserver.domain.user.dtos;
import com.team200.spyglassserver.domain.user.model.User;
import lombok.*;

@Getter
@ToString
public class UserDTO {
    private String id;
    private String fullName;
    private Integer numberOfGoals;

    public UserDTO(User userProfile){
        id = userProfile.getId();
        fullName = String.format("%s %s",userProfile.getFirstName(),userProfile.getLastName());
        numberOfGoals = userProfile.getGoals().size();
    }
}