package com.team200.spyglassserver.domain.goal.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team200.spyglassserver.domain.core.enums.CompletionStatus;
import com.team200.spyglassserver.domain.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;


@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String picture;
    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date goalStart;
    @Temporal(TemporalType.DATE)
    private Date targetDate;
    private Double targetAmount;
    private Double currentAmount;
    private CompletionStatus completionStatus;
    @ManyToOne
    @JsonBackReference
    private User owner;

    @PrePersist
    protected void onCreate() {
        goalStart = new Date();
        if(completionStatus == null)
            completionStatus = CompletionStatus.NOT_STARTED;
        if(currentAmount == null)
            currentAmount = 0.0;
    }

    @PreUpdate
    protected void onUpdate() {
        if(currentAmount != 0 && !currentAmount.equals(targetAmount))
            completionStatus = CompletionStatus.IN_PROGRESS;
        else
            completionStatus = CompletionStatus.COMPLETE;
    }

    public Goal(String title, String description, Date targetDate,Double targetAmount) {
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.targetAmount = targetAmount;
    }

    public Goal(String title, String description, Date goalStart, Date targetDate, Double targetAmount, Double currentAmount, CompletionStatus completionStatus, User owner) {
        this.title = title;
        this.description = description;
        this.goalStart = goalStart;
        this.targetDate = targetDate;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.completionStatus = completionStatus;
        this.owner = owner;
    }

    public Goal(String picture, String title, String description, Date goalStart, Date targetDate, Double targetAmount, Double currentAmount, CompletionStatus completionStatus, User owner) {
        this.picture = picture;
        this.title = title;
        this.description = description;
        this.goalStart = goalStart;
        this.targetDate = targetDate;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.completionStatus = completionStatus;
        this.owner = owner;
    }
}
