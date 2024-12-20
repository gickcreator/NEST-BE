package ssu.opensource.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ssu.opensource.service.taskStatus.TaskStatusRetriever;
import ssu.opensource.service.taskStatus.TaskStatusService;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleService {
    private final TaskStatusRetriever taskStatusRetriever;
    private final TaskStatusService taskStatusService;

    @Async
    @Scheduled(cron = "0 0 0 * * *")
    public void updateDeferred(){
        taskStatusRetriever.findAllByTargetDate(LocalDate.now().minusDays(1))
                .forEach(
                        taskStatus -> {
                            try{
                                taskStatusService.scheduleTasks(taskStatus);
                            } catch (Exception e){
                                log.error("Error occurred while updating task status : {}", e.getMessage());
                            }
                        }
                );
    }
}
