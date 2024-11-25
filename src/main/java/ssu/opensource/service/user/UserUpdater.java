package ssu.opensource.service.user;

import org.springframework.stereotype.Component;
import ssu.opensource.domain.User;

@Component
public class UserUpdater {

    public void updateName(final User user, final String givenName, final String familyName) {
        user.updateName(givenName, familyName);
    }
}
