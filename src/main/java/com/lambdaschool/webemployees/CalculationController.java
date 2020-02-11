package com.lambdaschool.webemployees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculationController {
    //localhost:2019/calc/salary/1/0.5
    //localhost:2019/calc/salary/2/0.03

    @GetMapping(value="/calc/salary/{empID}/{raise}",
                produces =  {"application/json"})

    public ResponseEntity<?> checkRaise(@PathVariable long empID,
                                        @PathVariable double raise) {
     Employee tempEmp = new Employee(WebEmployeesApplication.ourEmpList.findEmployee(e -> (e.getId() == empID)));
     tempEmp.setSalary(tempEmp.getSalary() * (1.0 + raise));
     return new ResponseEntity<>(tempEmp, HttpStatus.OK);
    }


}
