package com.team200.spyglassserver.domain.user.model;

import com.team200.spyglassserver.domain.goal.model.Goal;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Table(name = "users")
public class User {
    @Id
    private String id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Goal>goals;

    public User(@NonNull String firstName, @NonNull String lastName, @NonNull String email, List<Goal> goals) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.goals = goals;
    }
}
