package com.team200.spyglassserver.domain.user.model;

import com.team200.spyglassserver.domain.goal.model.Goal;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User {
    @Id
    private String id;


    private String firstName;


    private String lastName;


    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @NonNull
    private List<Goal>goals;

    public User( String firstName,  String lastName,  String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.goals = new ArrayList<>();
    }
}
