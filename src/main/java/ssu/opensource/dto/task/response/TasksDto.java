package ssu.opensource.dto.task.response;

import lombok.Builder;
import ssu.opensource.dto.task.request.TaskCreateDto;

import java.util.List;

@Builder
public record TasksDto(
        List<TaskDto> tasks
) {
    @Builder
    public record TaskDto(
            Long id,
            String name,
            TaskCreateDto.DeadLine deadLine,
            Boolean hasDescription,
            String status
    ) {
    }
}
