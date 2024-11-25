package ssu.opensource.service.googleCalendar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.GoogleCalendar;
import ssu.opensource.repository.GoogleCalendarRepository;

@Component
@RequiredArgsConstructor
public class GoogleCalendarRemover {
    private final GoogleCalendarRepository googleCalendarRepository;

    public void remove(final GoogleCalendar googleCalendar) {
        googleCalendarRepository.delete(googleCalendar);
    }
}
