package ssu.opensource.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssu.opensource.domain.User;
import ssu.opensource.dto.googleCalendar.UserEmailDto;
import ssu.opensource.dto.user.UserDto;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRetriever userRetriever;

    @Transactional(readOnly = true)
    public UserDto getUser(final Long userId){
        User user = userRetriever.findByUserId(userId);
        return UserDto.builder()
                .givenName(user.getGivenName())
                .familyName(user.getFamilyName())
                .image(user.getImage())
                .email(user.getEmail())
                .googleCalenders(
                        user.getGoogleCalendars().stream()
                                .map(googleCalender -> UserEmailDto.builder()
                                        .id(googleCalender.getId())
                                        .email(googleCalender.getEmail())
                                        .build()
                                )
                                .toList()
                ).build();
    }
}