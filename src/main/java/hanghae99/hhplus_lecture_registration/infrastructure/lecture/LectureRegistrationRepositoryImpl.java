package hanghae99.hhplus_lecture_registration.infrastructure.lecture;

import hanghae99.hhplus_lecture_registration.domain.lecture.Lecture;
import hanghae99.hhplus_lecture_registration.domain.lectureRegistraion.LectureRegistration;
import hanghae99.hhplus_lecture_registration.domain.lectureRegistraion.LectureRegistrationRepository;
import hanghae99.hhplus_lecture_registration.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRegistrationRepositoryImpl implements LectureRegistrationRepository {

    private final JpaLectureRegistrationRepository jpaLectureRegistrationRepository;

    @Override
    public LectureRegistration findByLectureAndAttendee(Lecture lecture, User user) {
        return jpaLectureRegistrationRepository.findByLectureAndAttendee(lecture, user);
    }

    @Override
    public List<LectureRegistration> findAllByAttendee(User user) {
        return jpaLectureRegistrationRepository.findByAttendee(user);
    }

    @Override
    public LectureRegistration save(LectureRegistration lectureRegistration) {
        return jpaLectureRegistrationRepository.save(lectureRegistration);
    }

}
