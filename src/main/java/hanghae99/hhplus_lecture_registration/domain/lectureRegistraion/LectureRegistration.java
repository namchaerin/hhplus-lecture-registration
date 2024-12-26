package hanghae99.hhplus_lecture_registration.domain.lectureRegistraion;

import hanghae99.hhplus_lecture_registration.domain.lecture.Lecture;
import hanghae99.hhplus_lecture_registration.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class LectureRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User attendee;

    @CreatedDate
    private LocalDateTime createdAt;


    public LectureRegistration(Lecture lecture, User attendee) {
        this.lecture = lecture;
        this.attendee = attendee;
    }


}
