package ssu.opensource.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ssu.opensource.domain.User;
import ssu.opensource.exception.NotFoundException;
import ssu.opensource.exception.code.NotFoundErrorCode;
import ssu.opensource.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserRetriever {
    private final UserRepository userRepository;

    public User findByUserId(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundErrorCode.NOT_FOUND_USER));
    }

    public User findBySerialIdAndEmailOrGet(final String serialId, final String givenName, final String familyName, final String picture, final String email){
        return userRepository.findBySerialIdAndEmail(serialId, email).orElseGet(
                ()-> userRepository.save(User.builder().serialId(serialId).givenName(givenName).familyName(familyName).image(picture).email(email).build())

        );
    }
}