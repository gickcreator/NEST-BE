package ssu.opensource.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.Task;
import ssu.opensource.repository.TaskRepository;

@Component
@RequiredArgsConstructor
public class TaskSaver {
    private final TaskRepository taskRepository;

    public Task save(final Task task) {
        return taskRepository.save(task);
    }
}

