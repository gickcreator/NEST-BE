package ssu.opensource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssu.opensource.domain.Task;
import ssu.opensource.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByUserAndId(final User user, final Long aLong);

    @Query(value="select t from Task t where t.user = :user " +
            "and exists (select tb from TimeBlock tb " +
            "where tb.taskStatus.task = t and tb.startTime between :startTime and :endTime " +
            "and tb.endTime between :startTime and :endTime)")
    List<Task> findAllByUserAndTimeBlocks(final User user, LocalDateTime startTime, LocalDateTime endTime);

    List<Task> findAllByUserAndAssignedDateIsNullOrderByCreatedAtDesc(final User user);

    List<Task> findAllByUserAndAssignedDateIsNullOrderByCreatedAtAsc(final User user);

    @Query(
            value = "select * from task t where t.user_id = :userId " +
                    "AND t.assigned_date is null " +
                    "order by abs(current_date - t.dead_line_date) asc nulls last, " +
                    "abs(extract(epoch from current_time - t.dead_line_time)) asc nulls last"
            , nativeQuery = true
    )
    List<Task> findAllByUserAndAssignedDateIsNullOrderByTimeDiffAsc(final Long userId);

    @Query(
            value = "select * from task t where t.user_id = :userId " +
                    "AND t.assigned_date is null " +
                    "order by abs(current_date - t.dead_line_date) desc nulls last, " +
                    "abs(extract(epoch from current_time - t.dead_line_time)) desc nulls last"
            , nativeQuery = true
    )
    List<Task> findAllByUserAndAssignedDateIsNullOrderByTimeDiffDesc(final Long userId);

    @Query(
            value = "select * from task t where t.user_id = :userId " +
                    "AND t.assigned_date is null " +
                    "AND t.dead_line_date <= current_date + interval '2 days' " +
                    "order by t.dead_line_date asc nulls last, t.dead_line_time asc nulls last"
            ,nativeQuery = true
    )
    List<Task> findAllUpcomingTasksByUserWitAssignedStatus(final Long userId);

    @Query(
            value = "select * from task t where t.user_id = :userId " +
                    "AND t.status = 'DEFERRED' AND t.assigned_date is null " +
                    "order by t.dead_line_date nulls last, t.dead_line_time nulls last"
            ,nativeQuery = true
    )
    List<Task> findAllDeferredTasksByUserWithStatus(final Long userId);

    // 설정한 기간 내의 assigned 된 일들 찾는 거
    @Query(
            value = "select count(*) from task t where t.user_id = :userId " +
                    "AND t.assigned_date BETWEEN :startDate AND :endDate"
            ,nativeQuery = true
    )
    Integer countAllAssignedTasksInPeriod(final Long userId, final LocalDate startDate, final LocalDate endDate);
}

