package lozardo.picpaydesafio.repositories;

import lozardo.picpaydesafio.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById (Long id);
    Optional<User> findUserByDocument(String document);
}
