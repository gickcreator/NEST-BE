package ssu.opensource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssu.opensource.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
