package ssu.opensource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssu.opensource.domain.GoogleCalendar;
import ssu.opensource.domain.User;

import java.util.List;
import java.util.Optional;

public interface GoogleCalendarRepository extends JpaRepository<GoogleCalendar, Long> {
    Optional<GoogleCalendar> findByIdAndUser(final Long id, final User user);
    List<GoogleCalendar> findAllByUser(final User user);

    Boolean existsByUserAndEmail(final User user, final String email);
}
