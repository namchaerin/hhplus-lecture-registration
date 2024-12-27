package hanghae99.hhplus_lecture_registration.controller.lecture;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanghae99.hhplus_lecture_registration.application.LectureFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LectureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    LectureFacade lectureFacade;

    private String prefixUrl;

    @BeforeEach
    public void init() {
        prefixUrl = "/lectures";
    }

    @DisplayName("강의목록 조회 api test")
    @Test
    void getLectureListTest() throws Exception {

        //given
        String requestBody = "{\"lectureDate\":\"2024-12-20\",\"registrationStatus\":\"AVAILABLE\"}";

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(prefixUrl + "/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @DisplayName("강의 신청 api test")
    @Test
    void applyLectureTest() throws Exception {

        //given
        Long lectureId = 1L;
        Long userId = 1L;

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post(prefixUrl + "/{lectureId}/apply", lectureId)
                .param("userId", String.valueOf(userId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

}