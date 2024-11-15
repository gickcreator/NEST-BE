package ssu.opensource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssu.opensource.domain.TaskStatus;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {

}
