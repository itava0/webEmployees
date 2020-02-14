package com.lambdaschool.webemployees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
//Beans - POJOs managed by Spring
@RequestMapping("/data")
public class EmployeeController {
    // localhost:2019/data/allemployees

    @GetMapping(value = "/allemployees",
            produces = {"application/json"})
    public ResponseEntity<?> getAllEmployees() {
        WebEmployeesApplication.ourEmpList.empList.sort((e1, e2) -> e1.getFname().compareToIgnoreCase(e2.getFname()));
        return new ResponseEntity<>(WebEmployeesApplication.ourEmpList.empList, HttpStatus.OK);
    }

    // localhost:2019/data/employee/2
    @GetMapping(value = "/employee/{id}",
            produces = {"application/json"})
    public ResponseEntity<?> getEmpDetail(
            @PathVariable
                    long id) {
        Employee rtnEmp = WebEmployeesApplication.ourEmpList.findEmployee(e -> (e.getId() == id));
        return new ResponseEntity<>(rtnEmp, HttpStatus.OK);
    }

    // localhost:2019/data/employees/s
    @GetMapping(value = "/employees/{letter}",
            produces = {"application/json"})
    public ResponseEntity<?> getEmployees(
            @PathVariable
                    char letter) {
        ArrayList<Employee> rtnEmps = WebEmployeesApplication.ourEmpList.
                findEmployees(e -> e.getFname().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        return new ResponseEntity<>(rtnEmps, HttpStatus.OK);
    }

    // localhost:2019/data/sortedemployees/s
    @GetMapping(value = "/sortedemployees/{letter}",
            produces = {"application/json"})
    public ResponseEntity<?> getSortedEmployees(
            @PathVariable
                    char letter) {
        ArrayList<Employee> rtnEmps = WebEmployeesApplication.ourEmpList.
                findEmployees(e -> e.getFname().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        rtnEmps.sort((e1, e2) -> ((int) (e1.getSalary() - e2.getSalary())));
        return new ResponseEntity<>(rtnEmps.get(0), HttpStatus.OK);
    }
}
