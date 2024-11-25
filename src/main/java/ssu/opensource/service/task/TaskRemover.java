package ssu.opensource.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.Task;
import ssu.opensource.repository.TaskRepository;

@Component
@RequiredArgsConstructor
public class TaskRemover {

    public final TaskRepository taskRepository;

    public void deleteTask(final Task task){
        taskRepository.delete(task);
    }

}
