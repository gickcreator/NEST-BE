package ssu.opensource.service.googleCalendar;

import org.springframework.stereotype.Component;
import ssu.opensource.domain.GoogleCalendar;

@Component
public class GoogleCalendarUpdater {
    public void updateTokens(
            final GoogleCalendar googleCalendar,
            final String accessToken
    ) {
        googleCalendar.updateTokens(accessToken);
    }
}
