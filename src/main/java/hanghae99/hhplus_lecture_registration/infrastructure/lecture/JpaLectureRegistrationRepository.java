package hanghae99.hhplus_lecture_registration.infrastructure.lecture;

import hanghae99.hhplus_lecture_registration.domain.lecture.Lecture;
import hanghae99.hhplus_lecture_registration.domain.lectureRegistraion.LectureRegistration;
import hanghae99.hhplus_lecture_registration.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JpaLectureRegistrationRepository extends JpaRepository<LectureRegistration, Long> {

    @Query(
            "SELECT LR " +
            "FROM LectureRegistration LR " +
            "WHERE LR.lecture = :lecture " +
            "AND LR.attendee = :user")
    LectureRegistration findByLectureAndAttendee(Lecture lecture, User user);


    @Query(
            "SELECT LR " +
            "FROM LectureRegistration LR " +
            "WHERE LR.attendee = :user")
    List<LectureRegistration> findByAttendee(User user);

}
