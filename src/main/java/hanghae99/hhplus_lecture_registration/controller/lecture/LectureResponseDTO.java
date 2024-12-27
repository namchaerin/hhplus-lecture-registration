package hanghae99.hhplus_lecture_registration.controller.lecture;

import hanghae99.hhplus_lecture_registration.domain.lecture.Lecture;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureResponseDTO {

    Long lectureId;
    String title;
    String lecturer;
    LocalDateTime lectureDateTime;

    public LectureResponseDTO(Lecture lecture) {
        this.lectureId = lecture.getId();
        this.title = lecture.getTitle();
        this.lecturer = lecture.getLecturer().getName();
        this.lectureDateTime = lecture.getLectureDateTime();
    }

}
