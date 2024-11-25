package ssu.opensource.service.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssu.opensource.domain.Task;
import ssu.opensource.domain.User;
import ssu.opensource.dto.task.TaskCreateDto;
import ssu.opensource.service.user.UserRetriever;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {

    private final TaskUpdater taskUpdater;
    private final TaskRetriever taskRetriever;
    private final TaskSaver taskSaver;
    private final TaskRemover taskRemover;

    private final UserRetriever userRetriever;

    // Staging Area Task 생성 API
    @Transactional
    public Task createTask(final Long userId, final TaskCreateDto taskCreateDto) {
        User user = userRetriever.findByUserId(userId);

        LocalDate deadLineDate = null;
        LocalTime deadLineTime = null;

        if (taskCreateDto.deadLine() != null) {
            deadLineDate = taskCreateDto.deadLine().date();
            deadLineTime = taskCreateDto.deadLine().time();
        }

        Task task = Task.builder()
                .user(user)
                .name(taskCreateDto.name())
                .deadLineDate(deadLineDate)
                .deadLineTime(deadLineTime)
                .build();
        return taskSaver.save(task);
    }

    public void removeTask(final Long userId, final Long taskId){
        User user= userRetriever.findByUserId(userId);
        Task task = taskRetriever.findByUserAndId(user, taskId);
        taskRemover.deleteTask(task);
    }

}
