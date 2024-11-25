package ssu.opensource.dto.googleCalendar;

import lombok.Builder;

@Builder
public record UserEmailDto(
        Long id,
        String email
) {
}
