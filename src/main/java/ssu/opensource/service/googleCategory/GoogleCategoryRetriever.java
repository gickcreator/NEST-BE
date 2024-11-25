package ssu.opensource.service.googleCategory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.GoogleCategory;
import ssu.opensource.repository.GoogleCategoryRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GoogleCategoryRetriever {
    private final GoogleCategoryRepository googleCategoryRepository;

    public List<GoogleCategory> findAllByGoogleCalendarId(final Long googleCalendarId) {
        return googleCategoryRepository.findAllByGoogleCalendarId(googleCalendarId);
    }
}
