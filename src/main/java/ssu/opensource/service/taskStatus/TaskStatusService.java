package ssu.opensource.service.taskStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssu.opensource.domain.Task;
import ssu.opensource.domain.TaskStatus;
import ssu.opensource.domain.TimeBlock;
import ssu.opensource.dto.type.Status;
import ssu.opensource.service.task.TaskRetriever;
import ssu.opensource.service.task.TaskUpdater;
import ssu.opensource.service.timeBlock.TimeBlockRemover;
import ssu.opensource.service.timeBlock.TimeBlockRetriever;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TaskStatusService {
    private final TaskUpdater taskUpdater;
    private final TaskStatusUpdater taskStatusUpdater;
    private final TaskStatusSaver taskStatusSaver;
    private final TaskRetriever taskRetriever;
    private final TimeBlockRemover timeBlockRemover;
    private final TimeBlockRetriever timeBlockRetriever;

    @Transactional
    public void scheduleTasks(TaskStatus taskStatus) {
        if (taskStatus.getStatus() == Status.TODO){
            Task task = taskRetriever.findById(taskStatus.getTask().getId());
            taskUpdater.updateStatus(task, Status.DEFERRED);
            taskUpdater.updateAssignedDate(task, null);
            taskStatusUpdater.updateStatus(taskStatus, Status.DEFERRED);
            taskStatusSaver.save(taskStatus);
            TimeBlock timeBlock = timeBlockRetriever.findByTaskStatus(taskStatus);
            if (timeBlock != null)
                timeBlockRemover.remove(timeBlock);
        } else if (taskStatus.getStatus() == Status.IN_PROGRESS){
            taskStatusSaver.save(
                    TaskStatus.builder()
                            .task(taskStatus.getTask())
                            .status(Status.IN_PROGRESS)
                            .targetDate(LocalDate.now())
                            .build()
            );
        }
    }
}