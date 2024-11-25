package ssu.opensource.service.timeBlock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.TaskStatus;
import ssu.opensource.domain.TimeBlock;
import ssu.opensource.repository.TimeBlockRepository;

@Component
@RequiredArgsConstructor
public class TimeBlockRetriever {

    private final TimeBlockRepository timeBlockRepository;

    public TimeBlock findByTaskStatus(
            final TaskStatus taskStatus
    ){
        return timeBlockRepository.findByTaskStatus(taskStatus).orElse(null);
    }
}
