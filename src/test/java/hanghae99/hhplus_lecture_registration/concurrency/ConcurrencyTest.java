package hanghae99.hhplus_lecture_registration.concurrency;

import hanghae99.hhplus_lecture_registration.application.LectureFacade;
import hanghae99.hhplus_lecture_registration.domain.lecture.Lecture;
import hanghae99.hhplus_lecture_registration.domain.lecture.LectureRepository;
import hanghae99.hhplus_lecture_registration.domain.user.User;
import hanghae99.hhplus_lecture_registration.domain.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
public class ConcurrencyTest {

    @Autowired
    private LectureFacade lectureFacade;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LectureRepository lectureRepository;

    private User user;
    private Lecture lecture;


    @DisplayName("두 명의 사용자가 동시에 신청할 때")
    @Test
    void testConcurrentUserRegistration() throws InterruptedException, ExecutionException {

        //given
        lecture = new Lecture("Java Programming", 30, 0); // 30명 정원
        lectureRepository.save(lecture);

        User user1 = new User("user1");
        userRepository.save(user1);

        User user2 = new User("user2");
        userRepository.save(user2);


        //when
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Void> task1 = () -> {
            lectureFacade.registerLecture(lecture.getId(), user1.getId());
            return null;
        };

        Callable<Void> task2 = () -> {
            lectureFacade.registerLecture(lecture.getId(), user2.getId());
            return null;
        };

        List<Callable<Void>> tasks = List.of(task1, task2);
        List<Future<Void>> results = executor.invokeAll(tasks);

        // 결과 기다리기(예외 발생 여부 확인)
        for (Future<Void> result : results) {
            result.get();
        }

        //then
        lecture = lectureRepository.findById(lecture.getId()).get();
        assertEquals(2, lecture.getCurrentAttendeesCount());

        executor.shutdown();
    }

}
