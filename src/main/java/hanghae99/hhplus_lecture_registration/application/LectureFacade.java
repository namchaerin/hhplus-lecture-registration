package hanghae99.hhplus_lecture_registration.application;

import hanghae99.hhplus_lecture_registration.controller.lecture.LectureRequestDTO;
import hanghae99.hhplus_lecture_registration.controller.lecture.LectureResponseDTO;
import hanghae99.hhplus_lecture_registration.controller.lecture.RegistrationStatus;
import hanghae99.hhplus_lecture_registration.domain.lecture.Lecture;
import hanghae99.hhplus_lecture_registration.domain.lecture.LectureService;
import hanghae99.hhplus_lecture_registration.domain.lectureRegistraion.LectureRegistration;
import hanghae99.hhplus_lecture_registration.domain.lectureRegistraion.LectureRegistrationService;
import hanghae99.hhplus_lecture_registration.domain.user.User;
import hanghae99.hhplus_lecture_registration.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureFacade {

    private final UserService userService;
    private final LectureService lectureService;
    private final LectureRegistrationService lectureRegistrationService;


    public List<Lecture> getLectureList(LectureRequestDTO dto) {
        return lectureService.getLectureList(dto);
    }

    @Transactional
    public void registerLecture(Long lectureId, Long userId) {

        //사용자 조회
        User user = userService.getUserById(userId);

        //강의 조회 및 비관적 락을 통한 잠금
        Lecture lecture = lectureService.findLectureWithPessimisticLock(lectureId);

        // 중복 신청 확인
        lectureRegistrationService.checkDuplicateRegistration(lecture, user);

        // 신청 가능 여부 확인, 신청 인원 증가
        lectureService.increaseCurrentParticipants(lecture);

        // 강의 신청 이력 저장
        lectureRegistrationService.saveRegistrationHistory(lecture, user);

    }

    public List<LectureResponseDTO> getLecturesByStatus(Long userId, RegistrationStatus status) {

        User user = userService.getUserById(userId);

        if (status.equals(RegistrationStatus.COMPLETED)) {
            return lectureRegistrationService.getLectureRegistrationByUserId(user)
                    .stream()
                    .map(LectureRegistration::getLecture)
                    .map(LectureResponseDTO::new)
                    .toList();
        } else {
            throw new IllegalArgumentException("강의 신청 상태 요청값이 필요합니다.");
        }

    }

}
