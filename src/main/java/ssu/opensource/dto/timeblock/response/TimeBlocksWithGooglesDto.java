package ssu.opensource.dto.timeblock.response;

import lombok.Builder;
import ssu.opensource.dto.googleCalendar.GoogleSchedulesDto;

import java.util.List;

@Builder
public record TimeBlocksWithGooglesDto(
        List<TimeBlocksDto> tasks,
        List<GoogleSchedulesDto> googles
) {

}

