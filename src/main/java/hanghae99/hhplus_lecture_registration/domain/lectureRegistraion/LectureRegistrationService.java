package hanghae99.hhplus_lecture_registration.domain.lectureRegistraion;

import hanghae99.hhplus_lecture_registration.domain.lecture.Lecture;
import hanghae99.hhplus_lecture_registration.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LectureRegistrationService {

    private final LectureRegistrationRepository repository;


    public void checkDuplicateRegistration(Lecture lecture, User attendee) {
        if (Objects.nonNull(repository.findByLectureAndAttendee(lecture, attendee)))
            throw new IllegalStateException("이미 신청완료된 강의입니다.");
    }

    public LectureRegistration saveRegistrationHistory(Lecture lecture, User attendee) {
        LectureRegistration registration = new LectureRegistration(lecture, attendee);
        repository.save(registration);

        return registration;
    }

    public List<LectureRegistration> getLectureRegistrationByUserId(User user) {
        return repository.findAllByAttendee(user);
    }

}
