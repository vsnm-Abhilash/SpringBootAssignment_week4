package com.abhilash.sb.assignment_w4;

import com.abhilash.sb.assignment_w4.clients.EmployeeClient;
import com.abhilash.sb.assignment_w4.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AssignmentW4ApplicationTests {

	@Autowired
	EmployeeClient employeeClient;

	@Test
	@Order(3)
	void getAllEmployees() {
		List<EmployeeDTO> employeeDTOList= employeeClient.getAllEmployees();
		employeeDTOList.stream().forEach(System.out::println);
	}

	@Test
	@Order(1)
	void getEmployeeById() {
		EmployeeDTO employeeDTOList= employeeClient.getEmployeeById(1l);
		System.out.println(employeeDTOList);
	}

	@Test
	@Order(2)
	void createNewEmployee() {
		EmployeeDTO employeeDTO=new EmployeeDTO(null,"Slark","fishyfishy@dota.com",22,"ADMIN",
				90000.00, LocalDate.of(2024,1,1),true);
		EmployeeDTO savedEmployeeDTO= employeeClient.createNewEmployee(employeeDTO);
		System.out.println(savedEmployeeDTO);
	}


}
