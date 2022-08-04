package com.team200.spyglassserver.domain.goal.repo;

import com.team200.spyglassserver.domain.core.enums.Status;
import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface GoalRepo extends JpaRepository<Goal, Long> {
    Optional<Goal>findById(Long id);
    Optional<Goal> findByTitle(String title);

    List<Goal> findByTargetDate(Date date);

    List<Goal> findByGoalDate(Date date);

    Optional<List<Goal>>findByOwnerAndStatus(User owner, Status status);
    List<Goal>findByTargetAmount(Double targetAmount);
}
