package com.lambdaschool.webemployees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Beans - POJOs managed by Spring
@RequestMapping("/data")
public class EmployeeController {
    // localhost:2019/data/allemployees

    @GetMapping(value = "/allemployees",
            produces = {"application/json"})
    public ResponseEntity<?> getAllEmployees()
    {
        WebEmployeesApplication.ourEmpList.empList.sort((e1, e2) -> e1.getFname().compareToIgnoreCase(e2.getFname()));
        return new ResponseEntity<>(WebEmployeesApplication.ourEmpList.empList, HttpStatus.OK);
    }

    //localhost:2019/data/employee/2

    //localhost:2019/data/employees/s
}
