package ssu.opensource.repository;

import org.springframework.data.repository.CrudRepository;
import ssu.opensource.domain.GoogleSchedule;

import java.util.List;

public interface GoogleScheduleRepository extends CrudRepository<GoogleSchedule, String> {
    List<GoogleSchedule> findAllByGoogleCalendarId(Long googleCalendarId);
}
