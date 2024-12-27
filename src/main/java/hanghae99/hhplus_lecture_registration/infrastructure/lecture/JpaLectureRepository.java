package hanghae99.hhplus_lecture_registration.infrastructure.lecture;

import hanghae99.hhplus_lecture_registration.controller.lecture.RegistrationStatus;
import hanghae99.hhplus_lecture_registration.domain.lecture.Lecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaLectureRepository extends JpaRepository<Lecture, Long> {

    @Query(
        "SELECT L " +
        "FROM Lecture L " +
        "WHERE " +
            "((:status = 'AVAILABLE' AND L.maxAttendees < L.currentAttendeesCount) OR " +
            "(:status = 'UNAVAILABLE' AND L.maxAttendees = L.currentAttendeesCount)) AND " +
            "L.lectureDateTime BETWEEN :from AND :to" )
    List<Lecture> getLectureList(RegistrationStatus status, LocalDateTime from, LocalDateTime to);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT L FROM Lecture L WHERE L.id = :lectureId")
    Optional<Lecture> findByIdWithPessimisticLock(Long lectureId);

    @Query("SELECT L FROM Lecture L WHERE L.lectureDateTime BETWEEN :from AND :to")
    List<Lecture> findAllByLectureDateTimeBetween(LocalDateTime from, LocalDateTime to);

}
