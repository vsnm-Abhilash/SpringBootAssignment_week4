package com.abhilash.sb.assignment_w4.clients.impl;

import com.abhilash.sb.assignment_w4.advices.ApiResponse;
import com.abhilash.sb.assignment_w4.clients.EmployeeClient;
import com.abhilash.sb.assignment_w4.dto.EmployeeDTO;
import com.abhilash.sb.assignment_w4.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {
    private final RestClient getEmployeeServiceRestClient;
    @Override
    public List<EmployeeDTO> getAllEmployees() {

      try{
          ApiResponse<List<EmployeeDTO>> employeeDTOList=getEmployeeServiceRestClient
                  .get()
                  .uri("employees")
                  .retrieve()
                  .body(new ParameterizedTypeReference<>() {
                  });

          return employeeDTOList.getData();
      }
      catch (Exception e){
          throw new RuntimeException(e);
      }

    }

    @Override
    public EmployeeDTO getEmployeeById(Long EmployeeId) {
        try{
            ApiResponse<EmployeeDTO> employeeDTO=getEmployeeServiceRestClient
                    .get()
                    .uri("employees/{employeeId}",EmployeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){
                    });

            return employeeDTO.getData();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try{
            ApiResponse<EmployeeDTO> newEmployee=getEmployeeServiceRestClient
                    .post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            ((request, response) -> {
                                System.out.println("Error Occured "+new String(response.getBody().readAllBytes()));
                                throw new ResourceNotFoundException("could not create the employee");
                            }))
                    .body(new ParameterizedTypeReference<>() {
                    });
            return newEmployee.getData();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
