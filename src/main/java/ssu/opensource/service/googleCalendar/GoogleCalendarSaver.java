package ssu.opensource.service.googleCalendar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.GoogleCalendar;
import ssu.opensource.repository.GoogleCalendarRepository;

@Component
@RequiredArgsConstructor
public class GoogleCalendarSaver {
    private final GoogleCalendarRepository googleCalendarRepository;

    public GoogleCalendar save(final GoogleCalendar googleCalendar) {
        return googleCalendarRepository.save(googleCalendar);
    }
}