package hanghae99.hhplus_lecture_registration.domain.lectureRegistraion;

import hanghae99.hhplus_lecture_registration.domain.lecture.Lecture;
import hanghae99.hhplus_lecture_registration.domain.user.User;

import java.util.List;

public interface LectureRegistrationRepository {

    LectureRegistration findByLectureAndAttendee(Lecture lecture, User user);

    List<LectureRegistration> findAllByAttendee(User user);

    LectureRegistration save(LectureRegistration lectureRegistration);

}
