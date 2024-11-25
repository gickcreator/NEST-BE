package ssu.opensource.service.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssu.opensource.domain.Task;
import ssu.opensource.domain.TaskStatus;
import ssu.opensource.domain.TimeBlock;
import ssu.opensource.domain.User;
import ssu.opensource.dto.task.request.TaskCreateDto;
import ssu.opensource.dto.task.request.TaskStatusDto;
import ssu.opensource.dto.type.Status;
import ssu.opensource.exception.BusinessException;
import ssu.opensource.exception.IllegalArgumentException;
import ssu.opensource.exception.code.BusinessErrorCode;
import ssu.opensource.exception.code.IllegalArgumentErrorCode;
import ssu.opensource.service.taskStatus.TaskStatusRemover;
import ssu.opensource.service.taskStatus.TaskStatusRetriever;
import ssu.opensource.service.taskStatus.TaskStatusSaver;
import ssu.opensource.service.taskStatus.TaskStatusUpdater;
import ssu.opensource.service.timeBlock.TimeBlockRemover;
import ssu.opensource.service.timeBlock.TimeBlockRetriever;
import ssu.opensource.service.user.UserRetriever;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {

    private final TaskUpdater taskUpdater;
    private final TaskRetriever taskRetriever;
    private final TaskSaver taskSaver;
    private final TaskRemover taskRemover;

    private final UserRetriever userRetriever;

    private final TaskStatusRetriever taskStatusRetriever;
    private final TaskStatusSaver taskStatusSaver;
    private final TaskStatusRemover taskStatusRemover;
    private final TaskStatusUpdater taskStatusUpdater;

    private final TimeBlockRetriever timeBlockRetriever;
    private final TimeBlockRemover timeBlockRemover;

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

    @Transactional
    public void updateStatus(
            final Long userId,
            final Long taskId,
            final TaskStatusDto taskStatusDto
    ) {
        User user = userRetriever.findByUserId(userId);
        Task task = taskRetriever.findByUserAndId(user, taskId);
        if (taskStatusDto.targetDate() == null){    //target area에서 staging area로 넘어갈 경우
            taskUpdater.updateAssignedDate(task, null);
            if (taskStatusRetriever.existsByTaskAndStatus(task, Status.DEFERRED)){
                taskUpdater.updateStatus(task, Status.DEFERRED);
                taskStatusSaver.save(
                        TaskStatus.builder()
                                .task(task)
                                .status(Status.DEFERRED)
                                .targetDate(LocalDate.now())
                                .build()
                );
            } else {
                taskUpdater.updateStatus(task, Status.TODO);
            }
            taskStatusRemover.removeAll(taskStatusRetriever.findAllByTask(task));
        } else {
            if(taskStatusDto.status() == null)
                throw new IllegalArgumentException(IllegalArgumentErrorCode.INVALID_ARGUMENTS);
            Status status = Status.fromContent(taskStatusDto.status());
            if (task.getAssignedDate() == null) {   //staging area에서 할당될 때
                if (status == Status.TODO) {
                    if (taskStatusDto.targetDate().isBefore(LocalDate.now())) // 할당 하려는 날짜가 now 보다 이전이면 예외
                        throw new BusinessException(BusinessErrorCode.BUSINESS_TODAY);
                } else if (status != Status.DONE) { // 완료랑 미완료만 staging area에서 가질 수 있으므로, 아니면 예외
                    throw new IllegalArgumentException(IllegalArgumentErrorCode.INVALID_ARGUMENTS);
                }
                // task의 assignedDate를 targetDate로 업데이트하고, 새로운 TaskStatus를 저장
                taskUpdater.updateAssignedDate(task, taskStatusDto.targetDate());
                taskStatusSaver.save(
                        TaskStatus.builder()
                                .task(task)
                                .status(status)
                                .targetDate(taskStatusDto.targetDate())
                                .build()
                );
            } else {    //target date area에서 수정될 때
                if ((status.equals(Status.DONE) || status.equals(Status.IN_PROGRESS))
                        && taskStatusDto.targetDate().isBefore(LocalDate.now())
                ) {    //완료 = targetDate 이후 삭제, 진행 중 = targetDate이후 값 변경
                    taskStatusRemover.removeAll(
                            taskStatusRetriever.findAllByTaskAndTargetDateGreaterThan(
                                    task, taskStatusDto.targetDate()
                            )
                    );
                    if (status.equals(Status.IN_PROGRESS)) {
                        List<TaskStatus> taskStatuses = new ArrayList<>();
                        for (LocalDate date = taskStatusDto.targetDate().plusDays(1);
                             date.isBefore(LocalDate.now().plusDays(1));
                             date = date.plusDays(1)
                        ) {
                            taskStatuses.add(
                                    TaskStatus.builder()
                                            .task(task)
                                            .status(status)
                                            .targetDate(date)
                                            .build()
                            );
                        }
                        taskStatusSaver.saveAll(taskStatuses);
                    }
                } else if (status.equals(Status.TODO)) {
                    taskStatusRemover.removeAll(
                            taskStatusRetriever.findAllByTaskAndTargetDateNot(task, taskStatusDto.targetDate())
                    );
                    if (taskStatusDto.targetDate().isBefore(LocalDate.now())) {
                        status = Status.DEFERRED;
                        taskUpdater.updateAssignedDate(task, null);
                    }
                }
                TaskStatus taskStatus = taskStatusRetriever.findByTaskAndTargetDate(
                        task, taskStatusDto.targetDate()
                );
                if (status.equals(Status.DEFERRED)) {
                    TimeBlock timeBlock = timeBlockRetriever.findByTaskStatus(taskStatus);
                    if (timeBlock != null)
                        timeBlockRemover.remove(timeBlock);
                }
                taskStatusUpdater.updateStatus(taskStatus, status);
            }
            taskUpdater.updateStatus(task, status);
        }
    }

}