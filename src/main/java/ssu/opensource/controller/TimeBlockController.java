package ssu.opensource.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssu.opensource.annotation.UserId;
import ssu.opensource.dto.timeblock.request.TimeBlockRequestDto;
import ssu.opensource.dto.timeblock.response.TimeBlocksWithGooglesDto;
import ssu.opensource.service.timeBlock.TimeBlockService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TimeBlockController {
    private final TimeBlockService timeBlockService;

    @PostMapping("/{taskId}/time-blocks")
    public ResponseEntity<Void> createTimeBlock(
            @UserId final Long userId,
            @PathVariable final Long taskId,
            @RequestBody @Valid final TimeBlockRequestDto timeBlockRequestDto
    ) {
        URI uri = URI.create(timeBlockService.create(userId, taskId, timeBlockRequestDto).getId().toString());
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping("/{taskId}/time-blocks/{timeBlockId}")
    public ResponseEntity<Void> updateTimeBlock(
            @UserId final Long userId,
            @PathVariable final Long taskId,
            @PathVariable final Long timeBlockId,
            @RequestBody @Valid final TimeBlockRequestDto timeBlockRequestDto
    ) {
        timeBlockService.update(userId, taskId, timeBlockId, timeBlockRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{taskId}/time-blocks/{timeBlockId}")
    public ResponseEntity<Void> deleteTimeBlock(
            @UserId final Long userId,
            @PathVariable final Long taskId,
            @PathVariable final Long timeBlockId
    ) {
        timeBlockService.delete(userId, taskId, timeBlockId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/time-blocks")
    public ResponseEntity<TimeBlocksWithGooglesDto> getTimeBlocks(
            @UserId final Long userId,
            @RequestParam @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul") final LocalDate startDate,
            @RequestParam final Integer range,
            @RequestParam(required = false) final List<String> categories
    ) {
        return ResponseEntity.ok(timeBlockService.getTimeBlocksWithGoogle(userId, startDate, range, categories));
    }
}

