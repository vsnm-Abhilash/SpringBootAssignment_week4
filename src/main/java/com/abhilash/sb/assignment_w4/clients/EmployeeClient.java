package com.abhilash.sb.assignment_w4.clients;

import com.abhilash.sb.assignment_w4.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long EmployeeId);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}
