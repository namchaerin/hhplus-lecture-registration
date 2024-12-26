package hanghae99.hhplus_lecture_registration.infrastructure.lecture;

import hanghae99.hhplus_lecture_registration.controller.lecture.RegistrationStatus;
import hanghae99.hhplus_lecture_registration.domain.lecture.Lecture;
import hanghae99.hhplus_lecture_registration.domain.lecture.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final JpaLectureRepository jpaLectureRepository;


    @Override
    public Optional<Lecture> findById(Long id) {
        return jpaLectureRepository.findById(id);
    }

    @Override
    public Lecture save(Lecture lecture) {
        return jpaLectureRepository.save(lecture);
    }

    @Override
    public List<Lecture> getLectureList(RegistrationStatus status, LocalDateTime from, LocalDateTime to) {
        return jpaLectureRepository.getLectureList(status, from, to);
    }

    @Override
    public Optional<Lecture> findByIdWithPessimisticLock(Long lectureId) {
        return jpaLectureRepository.findByIdWithPessimisticLock(lectureId);
    }

    @Override
    public List<Lecture> findAllByLectureDateTimeBetween(LocalDateTime from, LocalDateTime to) {
        return jpaLectureRepository.findAllByLectureDateTimeBetween(from, to);
    }

}
