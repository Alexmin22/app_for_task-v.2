package com.example.app_for_task;
import com.example.app_for_task.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.ServiceLoader;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(ServiceTestParamResolver.class)
@RequiredArgsConstructor
class AppForTaskApplicationTests {

	private ServiceTest serviceTest;
	private EmployeeService employeeService;


	@BeforeEach
	void prepare(ServiceTest serviceTest, EmployeeService employeeService) {
		this.serviceTest = serviceTest;
		this.employeeService = employeeService;

		System.out.println("Before each: " + this);
	}

	@Test
	void checkLoginWhenNameOrPasswordIsNull() {
		assertAll (
				() -> {
					var exeption = assertThrows(IllegalArgumentException.class,
							() -> serviceTest.login(null, employeeService.getById(1).getPassword()));
					assertThat(exeption.getMessage()).isEqualTo("Пустое поле имя!");
				},

				() -> {
					var exeption = assertThrows(IllegalArgumentException.class,
				() -> serviceTest.login(employeeService.getById(1).getUsername(), null));
					assertThat(exeption.getMessage()).isEqualTo("Пустое поле пароль!");
				});
	}

	@Test
	void contextLoads() {
	}

}
