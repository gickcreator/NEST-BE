package ssu.opensource.service.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.Task;
import ssu.opensource.dto.task.request.TaskUpdateDto;
import ssu.opensource.dto.type.Status;

import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@Component
public class TaskUpdater {


    public void updateStatus(
            final Task task,
            final Status status
    ) {
        task.updateStatus(status);
    }

    public void updateAssignedDate(
            final Task task,
            final LocalDate assignedDate
    ) {
        task.updateAssignedDate(assignedDate);
    }

    // Task 설명 수정 PATCH API
    public void editDetails(
            final Task task,
            final TaskUpdateDto taskUpdateDto
    ) {
        LocalDate deadLineDate = null;
        LocalTime deadLineTime = null;

        if (taskUpdateDto.deadLine() != null) {
            deadLineDate = taskUpdateDto.deadLine().date();
            deadLineTime = taskUpdateDto.deadLine().time();
        }
        task.updateTask(taskUpdateDto.name(), taskUpdateDto.description(), deadLineDate, deadLineTime);
    }
}
