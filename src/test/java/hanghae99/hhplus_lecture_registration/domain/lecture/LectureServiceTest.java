package hanghae99.hhplus_lecture_registration.domain.lecture;

import hanghae99.hhplus_lecture_registration.controller.lecture.LectureRequestDTO;
import hanghae99.hhplus_lecture_registration.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @Mock
    LectureRepository lectureRepository;

    @InjectMocks
    LectureService lectureService;

    private List<Lecture> lectures;

    @BeforeEach
    void init() {
        User user = new User("user");
        lectures = Arrays.asList(
                new Lecture(1L, "lecture1", user, 30, 30, LocalDateTime.of(2024, 12, 20, 12, 2, 3)),
                new Lecture(2L, "lecture2", user, 30, 10, LocalDateTime.of(2024, 12, 21, 12, 2, 3)),
                new Lecture(3L, "lecture3", user, 30, 30, LocalDateTime.of(2024, 12, 20, 14, 0, 0)),
                new Lecture(4L, "lecture4", user, 30, 20, LocalDateTime.of(2024, 12, 20, 12, 2, 3)),
                new Lecture(5L, "lecture5", user, 30, 9, LocalDateTime.of(2024, 12, 20, 12, 2, 3)));
    }

    @DisplayName("강의 목록 조회 테스트")
    @Test
    void getLectureListTest() {

        //given
        LectureRequestDTO requestDTO = new LectureRequestDTO(LocalDate.of(2024, 12, 20), null);
        when(lectureRepository.findAllByLectureDateTimeBetween(requestDTO.getLectureDate().atStartOfDay(), requestDTO.getLectureDate().plusDays(1L).atStartOfDay()))
                .thenReturn(lectures.stream()
                                    .filter(i -> i.getLectureDateTime().isAfter(requestDTO.getLectureDate().atStartOfDay()) && i.getLectureDateTime().isBefore(requestDTO.getLectureDate().plusDays(1L).atStartOfDay()))
                                    .toList());

        //when
        List<Lecture> lectureList = lectureService.getLectureList(requestDTO);

        //then
        assertEquals(lectureList.size(), 4);

    }

}