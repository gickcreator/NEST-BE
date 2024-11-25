package ssu.opensource.service.googleSchedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.GoogleSchedule;
import ssu.opensource.repository.GoogleScheduleRepository;

@Component
@RequiredArgsConstructor
public class GoogleScheduleSaver {
    private final GoogleScheduleRepository googleScheduleRepository;

    public void save(final GoogleSchedule googleSchedule) {
        googleScheduleRepository.save(googleSchedule);
    }
}
