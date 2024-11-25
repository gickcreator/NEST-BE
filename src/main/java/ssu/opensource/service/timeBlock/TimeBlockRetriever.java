package ssu.opensource.service.timeBlock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.Task;
import ssu.opensource.domain.TaskStatus;
import ssu.opensource.domain.TimeBlock;
import ssu.opensource.domain.User;
import ssu.opensource.dto.timeblock.response.TimeBlockDto;
import ssu.opensource.exception.NotFoundException;
import ssu.opensource.exception.code.NotFoundErrorCode;
import ssu.opensource.repository.TimeBlockRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TimeBlockRetriever {
    private final TimeBlockRepository timeBlockRepository;

    public TimeBlock findByTaskIdAndTargetDate(final Task task, final LocalDate targetDate){
        LocalDateTime startOfDay = targetDate.atStartOfDay();
        LocalDateTime endOfDay = targetDate.atTime(23, 59, 59);
        return timeBlockRepository.findByTaskIdAndTargetDate(task, startOfDay, endOfDay).orElse(null);
    }

    public TimeBlock findByTaskAndId(
            final Task task,
            final Long id
    ) {
        return timeBlockRepository.findByTaskAndId(task, id).orElseThrow(
                () -> new NotFoundException(NotFoundErrorCode.NOT_FOUND_TIME_BLOCK)
        );
    }
    public Boolean existsByTaskUserAndStartTimeBetweenAndEndTimeBetween(
            final User user,
            final LocalDateTime startTime,
            final LocalDateTime endTime
    ) {
        return timeBlockRepository.existsByTaskUserAndStartTimeBetweenAndEndTimeBetween(user, startTime, endTime);
    }

    public Boolean existsByTaskUserAndStartTimeBetweenAndEndTimeBetweenAndIdNot(
            final User user,
            final Long id,
            final LocalDateTime startTime,
            final LocalDateTime endTime
    ) {
        return timeBlockRepository.existsByTaskUserAndStartTimeBetweenAndEndTimeBetweenAndIdNot(user, id, startTime, endTime);
    }

    public Boolean existsByTaskAndStartTimeBetweenAndEndTimeBetween(
            final Task task,
            final LocalDateTime startTime,
            final LocalDateTime endTime
    ) {
        return timeBlockRepository.existsByTaskAndStartTimeBetweenAndEndTimeBetween(task, startTime, endTime);
    }

    public List<TimeBlockDto> findAllByTaskIdAndTimeRange(
            final Task task,
            final LocalDateTime startTime,
            final LocalDateTime endTime
    ) {
        return timeBlockRepository.findAllByTaskIdAndTimeRange(task, startTime, endTime);
    }

    public TimeBlock findByTaskStatus(
            final TaskStatus taskStatus
    ){
        return timeBlockRepository.findByTaskStatus(taskStatus).orElse(null);
    }
}

