package ssu.opensource.service.googleCategory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.GoogleCategory;
import ssu.opensource.repository.GoogleCategoryRepository;

@Component
@RequiredArgsConstructor
public class GoogleCategorySaver {
    private final GoogleCategoryRepository googleCategoryRepository;

    public void save(final GoogleCategory googleCategory) {
        googleCategoryRepository.save(googleCategory);
    }
}

