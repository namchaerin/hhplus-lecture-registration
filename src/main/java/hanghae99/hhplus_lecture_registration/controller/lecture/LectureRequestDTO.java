package hanghae99.hhplus_lecture_registration.controller.lecture;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LectureRequestDTO {

    private LocalDate lectureDate;
    private RegistrationStatus registrationStatus;

    public LectureRequestDTO(LocalDate lectureDate, RegistrationStatus registrationStatus) {
        this.lectureDate = lectureDate;
        this.registrationStatus = registrationStatus;
    }

}
