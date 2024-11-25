package ssu.opensource.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssu.opensource.annotation.UserId;
import ssu.opensource.dto.task.request.TaskCreateDto;
import ssu.opensource.dto.task.request.TaskStatusDto;
import ssu.opensource.service.task.TaskService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    // Staging Area 에 새로운 Task 생성하는 POST API
    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(
            @UserId final Long userId,
            @RequestBody final TaskCreateDto taskCreateDto
    ){
        return ResponseEntity.created(URI.create(taskService.createTask(userId, taskCreateDto).getId().toString())).build();
    }

    //Task 삭제 API
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @UserId final Long userId,
            @PathVariable(name="taskId") final Long taskId
    ){
        taskService.removeTask(userId, taskId);
        return ResponseEntity.noContent().build();
    }

    // task 상태 수정 PATCH API
    @PatchMapping("/tasks/{taskId}/status")
    public ResponseEntity<Void> updateStatus(
            @UserId final Long userId,
            @PathVariable final Long taskId,
            @RequestBody final TaskStatusDto taskStatusDto
    ) {
        taskService.updateStatus(userId, taskId, taskStatusDto);
        return ResponseEntity.noContent().build();
    }


}