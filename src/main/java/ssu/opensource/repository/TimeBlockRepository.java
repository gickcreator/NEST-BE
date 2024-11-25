package ssu.opensource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssu.opensource.domain.TaskStatus;
import ssu.opensource.domain.TimeBlock;

import java.util.Optional;

public interface TimeBlockRepository extends JpaRepository<TimeBlock, Long> {
    Optional<TimeBlock> findByTaskStatus(final TaskStatus taskStatus);

}
