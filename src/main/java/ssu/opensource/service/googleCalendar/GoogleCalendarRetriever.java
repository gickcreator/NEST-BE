package ssu.opensource.service.googleCalendar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.GoogleCalendar;
import ssu.opensource.domain.User;
import ssu.opensource.exception.NotFoundException;
import ssu.opensource.exception.code.NotFoundErrorCode;
import ssu.opensource.repository.GoogleCalendarRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GoogleCalendarRetriever {
    private final GoogleCalendarRepository googleCalendarRepository;
    public GoogleCalendar findByIdAndUser(final Long id, final User user) {
        return googleCalendarRepository.findByIdAndUser(id, user).orElseThrow(
                () -> new NotFoundException(NotFoundErrorCode.NOT_FOUND_GOOGLE_CALENDER)
        );
    }
    public boolean existsByUserAndEmail(final User user, final String email) {
        return googleCalendarRepository.existsByUserAndEmail(user, email);
    }

    public List<GoogleCalendar> findAllByUser(final User user) {
        return googleCalendarRepository.findAllByUser(user);
    }
}
