package ssu.opensource.dto.user;

import lombok.Builder;
import ssu.opensource.dto.googleCalendar.UserEmailDto;

import java.util.List;

@Builder
public record UserDto(
        String givenName,
        String familyName,
        String image,
        String email,
        List<UserEmailDto> googleCalenders
) {
}