package com.team200.spyglassserver.domain.user.dtos;
import com.team200.spyglassserver.domain.user.model.User;
import lombok.*;

@Getter
@ToString
public class UserDTO {
    private String id;
    private String fullName;
    private Integer numberOfGoals;

    public UserDTO(User user){
        id = user.getId();
        fullName = String.format("%s %s", user.getFirstName(), user.getLastName());
        //numberOfGoals = user.getGoals().size();
    }
}