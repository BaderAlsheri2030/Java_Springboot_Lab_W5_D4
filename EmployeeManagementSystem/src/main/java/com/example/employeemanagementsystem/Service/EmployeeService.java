package com.example.employeemanagementsystem.Service;

import com.example.employeemanagementsystem.Model.Employee;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.ArrayList;

@Service
public class EmployeeService {
    ArrayList<Employee> employees = new ArrayList<>();



    public ArrayList<Employee> getEmployees(){
        return employees;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public boolean deleteEmployee(String id){
        for (int i = 0; i <employees.size() ; i++) {
            if(employees.get(i).getId().equals(id)){
                employees.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateEmployee(String id, Employee employee){
        for (int i = 0; i <employees.size() ; i++) {
            if (employees.get(i).getId().equals(id)){
                employees.set(i,employee);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Employee> searchEmployeePosition(String position){
        ArrayList<Employee> positions = new ArrayList<>();
        if (position.equalsIgnoreCase("supervisor")||position.equalsIgnoreCase("coordinator")) {
            for (Employee e : employees) {
                if (e.getPosition().equalsIgnoreCase(position)) {
                    positions.add(e);
                }
            }
        }else return null;
        return positions;
    }


                                            //30            //50
    public ArrayList<Employee> searchByAge(int minAge, int maxAge){
        ArrayList<Employee> ageRange = new ArrayList<>();
        if (minAge >= 25 && maxAge >25) {
            for (Employee employee : employees) {
                if (employee.getAge() >= minAge && employee.getAge() <= maxAge) {
                    ageRange.add(employee);
                }
            }
        }else return null;

        return ageRange;
    }


    public boolean applyAnnualLeave(String id){
        for (Employee e: employees) {
            if (e.getId().equals(id)){
                if (!(e.isOnLeave())){
                    e.setOnLeave(true);
                    if (e.getAnnualLeave() >= 1){
                        e.setAnnualLeave(e.getAnnualLeave()-1);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Employee> getNoAnnualLeave(){
        ArrayList<Employee> noAnnualLeave = new ArrayList<>();
        for (Employee e: employees) {
            if (e.getAnnualLeave() == 0){
                noAnnualLeave.add(e);
            }
        }
        return noAnnualLeave;
    }


    public boolean promotion(String supervisor, String employeeId) {
        if (supervisor.equals("supervisor")) {
            for (Employee employee : employees) {
                if (employee.getId().equals(employeeId)) {
                    if (employee.getAge() >= 30) {
                        if (!employee.isOnLeave()) {
                            if (!(employee.getPosition().equals("supervisor"))) {
                                employee.setPosition("supervisor");
                                return true;
                            }
                        }
                    }

                }
            }

        }
        return false;

    }



}
