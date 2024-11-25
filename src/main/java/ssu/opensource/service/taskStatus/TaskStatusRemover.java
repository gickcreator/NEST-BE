package ssu.opensource.service.taskStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.TaskStatus;
import ssu.opensource.repository.TaskStatusRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskStatusRemover {
    private final TaskStatusRepository taskStatusRepository;

    public void remove(final TaskStatus taskStatus){
        taskStatusRepository.delete(taskStatus);
    }

    public void removeAll(final List<TaskStatus> taskStatuses){
        taskStatusRepository.deleteAll(taskStatuses);
    }
}
