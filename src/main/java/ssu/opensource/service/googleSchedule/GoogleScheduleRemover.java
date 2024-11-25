package ssu.opensource.service.googleSchedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.GoogleSchedule;
import ssu.opensource.repository.GoogleScheduleRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GoogleScheduleRemover {
    private final GoogleScheduleRepository googleScheduleRepository;

    public void removeAll(final List<GoogleSchedule> googleSchedules) {
        googleScheduleRepository.deleteAll(googleSchedules);
    }
}