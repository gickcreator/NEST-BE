package ssu.opensource.repository;

import org.springframework.data.repository.CrudRepository;
import ssu.opensource.domain.GoogleCategory;

import java.util.List;

public interface GoogleCategoryRepository extends CrudRepository<GoogleCategory, String> {
    List<GoogleCategory> findAllByGoogleCalendarId(final Long googleCalendarId);
}
