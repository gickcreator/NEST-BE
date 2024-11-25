package ssu.opensource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssu.opensource.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySerialIdAndEmail(final String serialId, final String email);

    Optional<User> findById(final Long userId);
}
