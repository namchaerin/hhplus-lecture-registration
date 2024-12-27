package hanghae99.hhplus_lecture_registration.application;

import hanghae99.hhplus_lecture_registration.domain.lecture.Lecture;
import hanghae99.hhplus_lecture_registration.domain.lecture.LectureRepository;
import hanghae99.hhplus_lecture_registration.domain.lecture.LectureService;
import hanghae99.hhplus_lecture_registration.domain.lectureRegistraion.LectureRegistration;
import hanghae99.hhplus_lecture_registration.domain.user.User;
import hanghae99.hhplus_lecture_registration.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
class LectureFacadeTest {

    @Autowired
    private LectureFacade lectureFacade;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LectureRepository lectureRepository;

    private Lecture lecture;
    private User user;
    private LectureRegistration lectureRegistration;

    @BeforeEach
    public void setUp() {
        user = new User("user");
        userRepository.save(user);

        lecture = new Lecture("lecture", 30, 0);
        lectureRepository.save(lecture);
    }

    @DisplayName("강의 신청 테스트 - 정원이 30명인 강의에 40명이 신청하는 경우_10명은 실패")
    @Test
    @Transactional
    public void registerLectureTest_WhenAttendeesExceed_ShouldFail() {

        //given

        //when
        for (int i = 0; i < 40; i++) {
            User attendee = new User("user" + (i + 2));
            userRepository.save(attendee);
            try {
                lectureFacade.registerLecture(lecture.getId(), attendee.getId());
            } catch (Exception e) {
                if (i >= 30) {
                    assertTrue(e instanceof IllegalStateException);
                    assertEquals("신청 가능 인원이 다 채워졌습니다.", e.getMessage());
                }
            }
        }

        //then
        assertEquals(30, lecture.getCurrentAttendeesCount());

    }

}