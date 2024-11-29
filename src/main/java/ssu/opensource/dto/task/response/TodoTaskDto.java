package ssu.opensource.dto.task.response;

import lombok.Builder;
import ssu.opensource.dto.task.request.TaskCreateDto;

import java.util.List;

@Builder
public record TodoTaskDto(
        List<TaskComponentDto> tasks
) {
    @Builder
    public record TaskComponentDto(
            Long id,
            String name,
            TaskCreateDto.DeadLine deadLine
    ) { }
}

