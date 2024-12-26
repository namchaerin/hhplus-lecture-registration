package hanghae99.hhplus_lecture_registration.domain.lecture;

import hanghae99.hhplus_lecture_registration.controller.lecture.LectureRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;


    public List<Lecture> getLectureList(LectureRequestDTO dto) {

        LocalDateTime from = dto.getLectureDate().atStartOfDay();
        LocalDateTime to = dto.getLectureDate().plusDays(1L).atStartOfDay();

        if (Objects.nonNull(dto.getRegistrationStatus())) {
            return lectureRepository.getLectureList(dto.getRegistrationStatus(), from, to);
        } else {
            return lectureRepository.findAllByLectureDateTimeBetween(from, to);
        }
    }

    public Lecture findLectureWithPessimisticLock(Long lectureId) {
        return lectureRepository.findByIdWithPessimisticLock(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다."));
    }

    public void increaseCurrentParticipants(Lecture lecture) {
        lecture.increaseCurrentAttendees();
        lectureRepository.save(lecture);
    }

}
