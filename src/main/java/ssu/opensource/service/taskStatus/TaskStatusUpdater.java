package ssu.opensource.service.taskStatus;

import org.springframework.stereotype.Component;
import ssu.opensource.domain.TaskStatus;
import ssu.opensource.dto.type.Status;

@Component
public class TaskStatusUpdater {
    public void updateStatus(final TaskStatus taskStatus, final Status status){
        taskStatus.updateStatus(status);
    }
}
