package ssu.opensource.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.Task;
import ssu.opensource.domain.User;
import ssu.opensource.exception.NotFoundException;
import ssu.opensource.exception.code.NotFoundErrorCode;
import ssu.opensource.repository.TaskRepository;

@Component
@RequiredArgsConstructor
public class TaskRetriever {

    private final TaskRepository taskRepository;

    public Task findByUserAndId(final User user, final Long taskId) {
        return taskRepository.findByUserAndId(user, taskId)
                .orElseThrow(() -> new NotFoundException(NotFoundErrorCode.NOT_FOUND_TASK));
    }
}
