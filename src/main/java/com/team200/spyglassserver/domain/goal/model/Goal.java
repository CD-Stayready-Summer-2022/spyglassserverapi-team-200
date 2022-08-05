package com.team200.spyglassserver.domain.goal.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team200.spyglassserver.domain.core.enums.CompletionStatus;
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
@Table(name = "goals")
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
    private CompletionStatus completionStatus;
    @NonNull
    @ManyToOne
    @JsonBackReference
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
