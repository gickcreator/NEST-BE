package ssu.opensource.service.timeBlock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ssu.opensource.domain.TimeBlock;
import ssu.opensource.repository.TimeBlockRepository;

@Component
@RequiredArgsConstructor
public class TimeBlockRemover {
    private final TimeBlockRepository timeBlockRepository;

    @Transactional
    public void remove(final TimeBlock timeBlock) {
        timeBlockRepository.delete(timeBlock);
    }
}
