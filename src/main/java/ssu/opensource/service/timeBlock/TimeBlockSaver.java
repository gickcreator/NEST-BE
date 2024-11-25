package ssu.opensource.service.timeBlock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.TimeBlock;
import ssu.opensource.repository.TimeBlockRepository;

@Component
@RequiredArgsConstructor
public class TimeBlockSaver {
    private final TimeBlockRepository timeBlockRepository;

    public TimeBlock save(final TimeBlock timeBlock) {
        return timeBlockRepository.save(timeBlock);
    }
}

