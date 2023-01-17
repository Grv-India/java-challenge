package jp.co.axa.apidemo.controllers;

import io.swagger.annotations.ApiOperation;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation("Welcome route")
    @GetMapping
    public String welcome(){
        return "Welcome to Employee Record System";
    }
    @ApiOperation("Fetch all employee records")
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    @ApiOperation("Fetch the employee record on the basis of passed unique id")
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @ApiOperation("Add the passed employee data to records")
    @PostMapping("/employees")
    public void saveEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        System.out.println("Employee Saved Successfully");
    }

    @ApiOperation("Delete employee data from records based on passed unique id")
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        return employeeService.deleteEmployee(employeeId);
    }

    @ApiOperation("Do the changes in existing employee record")
    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            if((employee.getName() !=null) && (employee.getName() != emp.getName())){
                emp.setName(employee.getName());
            }
            if((employee.getDepartment() !=null) && (employee.getDepartment() != emp.getDepartment())){
                emp.setDepartment(employee.getDepartment());
            }
            if((employee.getSalary() !=null) && (employee.getSalary() != emp.getSalary())){
                emp.setSalary(employee.getSalary());
            }
            return employeeService.updateEmployee(emp);
        }
        return new Employee();

    }

}
