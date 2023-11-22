package com.example.employeemanagementsystem.Controller;

import com.example.employeemanagementsystem.Model.Employee;
import com.example.employeemanagementsystem.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get")
public ResponseEntity getEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployees());
    }

    @PostMapping("/add")
public ResponseEntity addEmployee(@Valid @RequestBody Employee employee, Errors errors){
    if (errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    employeeService.addEmployee(employee);
    return ResponseEntity.status(HttpStatus.OK).body("Employee added");
}
@DeleteMapping("/delete/{id}")
public ResponseEntity deleteEmployee(@PathVariable String id){
boolean isDeleted = employeeService.deleteEmployee(id);
if (isDeleted){
    return ResponseEntity.status(HttpStatus.OK).body("employee exists and is deleted");
}
return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid id or Employee doesn't exist");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEmployee(@PathVariable String id, @Valid @RequestBody Employee employee, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isUpdated = employeeService.updateEmployee(id,employee);
        if (isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body("employee is updated");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Id or employee doesn't exist");
    }

    @GetMapping("/search/position/{position}")
    public ResponseEntity searchEmployeePosition(@PathVariable String position){
        if (employeeService.searchEmployeePosition(position) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid position input");
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.searchEmployeePosition(position));

    }

    @GetMapping("/search/agerange/{minAge}/{maxAge}")
    public ResponseEntity searchEmployeeByAge(@PathVariable int minAge,@PathVariable int maxAge){
        if (employeeService.searchByAge(minAge,maxAge) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid age range input");
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.searchByAge(minAge,maxAge));
    }

    @GetMapping("/applyforleave/{id}")
    public ResponseEntity applyAnnualLeave(@PathVariable String id){
        boolean isValid = employeeService.applyAnnualLeave(id);
        if (isValid){
            return ResponseEntity.status(HttpStatus.OK).body("on leave is set to true and reduced amount of annual leave by 1");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee doesn't exist or is on leave or has no annual leave");
    }

    @GetMapping("/get/annualleave")
    public ResponseEntity getNoAnnualLeave(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getNoAnnualLeave());
    }


    @GetMapping("/promote/{supervisor}/{employeeId}")
    public ResponseEntity promoteEmployee(@PathVariable String supervisor, @PathVariable String employeeId){
        boolean isValid = employeeService.promotion(supervisor,employeeId);
        if (isValid) {
            return ResponseEntity.status(HttpStatus.OK).body("Employee with ID:"+employeeId+" is promoted to a supervisor");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID doesn't exist or user not allowed to promote");
    }

















}
