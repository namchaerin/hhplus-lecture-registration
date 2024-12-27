package hanghae99.hhplus_lecture_registration.domain.user;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    User save(User user);

}
