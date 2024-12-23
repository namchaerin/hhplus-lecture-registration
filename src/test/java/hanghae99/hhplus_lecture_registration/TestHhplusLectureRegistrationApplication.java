package hanghae99.hhplus_lecture_registration;

import org.springframework.boot.SpringApplication;

public class TestHhplusLectureRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.from(HhplusLectureRegistrationApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
