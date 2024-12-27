package hanghae99.hhplus_lecture_registration.controller.lecture;

import hanghae99.hhplus_lecture_registration.application.LectureFacade;
import hanghae99.hhplus_lecture_registration.controller.dto.ApiResponse;
import hanghae99.hhplus_lecture_registration.domain.lecture.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureFacade lectureFacade;


    @GetMapping("/list")
    public ResponseEntity<List<Lecture>> getLectureList(
            @RequestBody LectureRequestDTO requestDTO) {
        return ResponseEntity.ok(lectureFacade.getLectureList(requestDTO));
    }

    @PostMapping("/{lectureId}/apply")
    public ResponseEntity<ApiResponse> applyLecture(
            @PathVariable Long lectureId,
            @RequestParam Long userId) {
        try {
            lectureFacade.registerLecture(lectureId, userId);
            return ResponseEntity
                    .ok(new ApiResponse(true, "수강 신청이 성공적으로 완료되었습니다."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ApiResponse(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "서버 오류가 발생했습니다. 다시 시도해 주세요."));
        }
    }

    @GetMapping("")
    public ResponseEntity<List<LectureResponseDTO>> getLecturesByStatus(
            @PathVariable Long userId,
            @RequestParam RegistrationStatus status) {
        return ResponseEntity.ok(lectureFacade.getLecturesByStatus(userId, status));

    }

}
