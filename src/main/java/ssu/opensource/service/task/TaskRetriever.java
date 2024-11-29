package ssu.opensource.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.Task;
import ssu.opensource.domain.User;
import ssu.opensource.exception.NotFoundException;
import ssu.opensource.exception.code.NotFoundErrorCode;
import ssu.opensource.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskRetriever {
    private final TaskRepository taskRepository;

    public Task findByUserAndId(final User user, final Long taskId) {
        return taskRepository.findByUserAndId(user, taskId)
                .orElseThrow(() -> new NotFoundException(NotFoundErrorCode.NOT_FOUND_TASK));
    }

    public Task findById(final Long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NotFoundErrorCode.NOT_FOUND_TASK)
        );
    }

    public List<Task> findAllByUserAndTimeBlocks(User user, LocalDateTime startTime, LocalDateTime endTime) {
        return taskRepository.findAllByUserAndTimeBlocks(user, startTime, endTime);
    }

    public List<Task> findAllByUserAndAssignedDateIsNullOrderByCreatedAtDesc(final User user) {
        return taskRepository.findAllByUserAndAssignedDateIsNullOrderByCreatedAtDesc(user);
    }

    public List<Task> findAllByUserAndAssignedDateIsNullOrderByCreatedAtAsc(final User user) {
        return taskRepository.findAllByUserAndAssignedDateIsNullOrderByCreatedAtAsc(user);
    }

    public List<Task> findAllByUserAndAssignedDateIsNullOrderByTimeDiffAsc(final User user) {
        return taskRepository.findAllByUserAndAssignedDateIsNullOrderByTimeDiffAsc(user.getId());
    }

    public List<Task> findAllByUserAndAssignedDateIsNullOrderByTimeDiffDesc(final User user) {
        return taskRepository.findAllByUserAndAssignedDateIsNullOrderByTimeDiffDesc(user.getId());
    }

    public List<Task> findAllUpcomingTasksByUserWitAssignedStatus(final Long userId){
        return taskRepository.findAllUpcomingTasksByUserWitAssignedStatus(userId);
    }

    public List<Task> findAllDeferredTasksByUserWithStatus(final Long userId){
        return taskRepository.findAllDeferredTasksByUserWithStatus(userId);
    }


}
