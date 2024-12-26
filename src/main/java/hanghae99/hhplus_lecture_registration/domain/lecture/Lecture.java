package hanghae99.hhplus_lecture_registration.domain.lecture;

import hanghae99.hhplus_lecture_registration.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User lecturer;

    private int maxAttendees = 30;

    private int currentAttendeesCount;

    private LocalDateTime lectureDateTime;

    public Lecture(Long id, String title, User lecturer, int maxAttendees, int currentAttendeesCount, LocalDateTime lectureDateTime) {
        this.id = id;
        this.title = title;
        this.lecturer = lecturer;
        this.maxAttendees = maxAttendees;
        this.currentAttendeesCount = currentAttendeesCount;
        this.lectureDateTime = lectureDateTime;
    }

    public Lecture(String title, int maxAttendees, int currentAttendeesCount) {
        this.title = title;
        this.maxAttendees = maxAttendees;
        this.currentAttendeesCount = currentAttendeesCount;
    }

    public boolean isAvailableForRegistration() {
        return currentAttendeesCount < maxAttendees;
    }

    public void increaseCurrentAttendees() {
        if (isAvailableForRegistration()) {
            currentAttendeesCount++;
        } else {
            throw new IllegalStateException("신청 가능 인원이 다 채워졌습니다.");
        }
    }

}
