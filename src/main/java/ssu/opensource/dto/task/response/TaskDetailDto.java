package ssu.opensource.dto.task.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import ssu.opensource.dto.task.request.TaskCreateDto;

import java.time.LocalDateTime;

@Builder
public record TaskDetailDto(
        String name,
        String description,
        TaskCreateDto.DeadLine deadLine,
        String status,
        TimeBlock timeBlock
) {
    @Builder
    public record TimeBlock(
            Long id,
            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
            LocalDateTime startTime,
            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
            LocalDateTime endTime
    ){}
}