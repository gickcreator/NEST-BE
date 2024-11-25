package ssu.opensource.service.googleSchedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.GoogleSchedule;
import ssu.opensource.repository.GoogleScheduleRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GoogleScheduleRetriever {
    private final GoogleScheduleRepository googleScheduleRepository;

    public GoogleSchedule findById(final Long googleCalendarId, final String categoryId) {
        String id = googleCalendarId + ":" + categoryId;
        return googleScheduleRepository.findById(id).orElse(null);
    }
    public List<GoogleSchedule> findAllByGoogleCalendarId(final Long googleCalendarId) {
        return googleScheduleRepository.findAllByGoogleCalendarId(googleCalendarId);
    }
}

