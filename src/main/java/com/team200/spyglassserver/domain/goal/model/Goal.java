package com.team200.spyglassserver.domain.goal.model;

import com.team200.spyglassserver.domain.core.enums.Status;
import com.team200.spyglassserver.domain.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String title;
    private String description;
    @NonNull
    @Temporal(TemporalType.DATE)
    private Date goalStart;
    @NonNull
    @Temporal(TemporalType.DATE)
    private Date targetDate;
    @NonNull
    private Double targetAmount;
    @NonNull
    private Double currentAmount;
    @NonNull
    private Status status;
    @NonNull
    @ManyToOne
    private User owner;

    @PrePersist
    protected void onCreate() {
        goalStart = new Date();
    }

    public Goal(@NonNull String title, String description, @NonNull Date targetDate, @NonNull Double targetAmount) {
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.targetAmount = targetAmount;
    }
}
