package hanghae99.hhplus_lecture_registration.infrastructure.user;

import hanghae99.hhplus_lecture_registration.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {


}
