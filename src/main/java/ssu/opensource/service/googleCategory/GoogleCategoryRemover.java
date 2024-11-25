package ssu.opensource.service.googleCategory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.GoogleCategory;
import ssu.opensource.repository.GoogleCategoryRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GoogleCategoryRemover {
    private final GoogleCategoryRepository googleCategoryRepository;

    public void removeAll(List<GoogleCategory> googleCategories) {
        googleCategoryRepository.deleteAll(googleCategories);
    }
}