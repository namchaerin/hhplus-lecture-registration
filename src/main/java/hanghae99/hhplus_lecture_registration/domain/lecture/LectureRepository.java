package hanghae99.hhplus_lecture_registration.domain.lecture;

import hanghae99.hhplus_lecture_registration.controller.lecture.RegistrationStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LectureRepository {

    Optional<Lecture> findById(Long id);

    Lecture save(Lecture lecture);

    List<Lecture> getLectureList(RegistrationStatus status, LocalDateTime from, LocalDateTime to);

    Optional<Lecture> findByIdWithPessimisticLock(Long lectureId);

    List<Lecture> findAllByLectureDateTimeBetween(LocalDateTime from, LocalDateTime to);

}
