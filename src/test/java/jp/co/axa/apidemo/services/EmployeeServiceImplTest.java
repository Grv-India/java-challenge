package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmployeeServiceImplTest {

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    @Mock
    EmployeeRepository repository;

    Long id = 1L;
    @BeforeEach
    void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
    }
    @org.junit.jupiter.api.Test
    void getEmployee() {
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setDepartment("hr");
        emp.setName("gaurav");
        emp.setSalary(123);

        when(repository.findById(1L)).thenReturn(Optional.of(emp));

        Employee empService = employeeServiceImpl.getEmployee(1L);
        String empName = empService.getName();
        System.out.println(empName);

        assertNotNull(empName);
        assertEquals("gaurav", empService.getName());
    }

    @Test
    void getEmployeeFailure(){
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setDepartment("hr");
        emp.setName("gaurav");
        emp.setSalary(123);

        when(repository.findById(1L)).thenReturn(Optional.of(emp));

        Employee empService = employeeServiceImpl.getEmployee(1L);
        String empName = empService.getName();
        System.out.println(empName);

        assertNotEquals("gary", empService.getName());
    }

    @Test
    void deleteEmployee(){
        Employee emp = new Employee();
        emp.setId(id);
        emp.setDepartment("hr");
        emp.setName("gaurav");
        emp.setSalary(123);
        repository.deleteById(id);
        assertEquals(employeeServiceImpl.deleteEmployee(id),"Employee Record deleted");
    }
}