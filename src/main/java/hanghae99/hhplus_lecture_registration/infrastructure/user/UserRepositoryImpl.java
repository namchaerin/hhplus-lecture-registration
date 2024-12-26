package hanghae99.hhplus_lecture_registration.infrastructure.user;

import hanghae99.hhplus_lecture_registration.domain.user.User;
import hanghae99.hhplus_lecture_registration.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }

}
