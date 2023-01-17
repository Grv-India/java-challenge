package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /** Fetch all the employees from H2 DB
     * @return A list of all the employees
     * @argument No arguments required
     * */
    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    /** Searches for the employee from H2 DB by unique id
     * @return If employee is found then return it otherwise returns null
     * @argument Long type id
     * For Performance improvement, cache logic is also applied to it.
     * */
    @Cacheable(cacheNames = "employee-cache", key = "#employeeId")
    public Employee getEmployee(Long employeeId) {
        System.out.println("Employee from db");
        return employeeRepository.findById(employeeId).orElse(null);
    }

    /** Save the employee data passed to H2 DB
     * @return void
     * @argument entity Employee
     * */
    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    /** Delete the employee from H2 DB based on unique id
     * First looks for the employee in the DB if found then perform the deletion
     * For performance improvement, caching logic is applied
     * @return String (Failure or Success)
     * @argument Long id
     * */
    @CacheEvict(cacheNames = "employee-cache", key = "#employeeId")
    public String deleteEmployee(Long employeeId){
        try{
            employeeRepository.deleteById(employeeId);
        }
        catch(Exception error) {
            return "Some issue in deleting the employee record";
        }
        return "Employee Record deleted";
    }

    /** Update the employee data in H2 DB
     * For performance improvement, caching logic is applied.
     * @return Updated Employee entity
     * @argument Employee entity
     * */
    @CachePut(cacheNames = "employee-cache", key = "#employee.id")
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}