package ssu.opensource.service.taskStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.TaskStatus;
import ssu.opensource.repository.TaskStatusRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskStatusSaver {
    private final TaskStatusRepository taskStatusRepository;

    public TaskStatus save(TaskStatus taskStatus) {
        return taskStatusRepository.save(taskStatus);
    }

    public void saveAll(List<TaskStatus> taskStatuses) {
        taskStatusRepository.saveAll(taskStatuses);
    }
}