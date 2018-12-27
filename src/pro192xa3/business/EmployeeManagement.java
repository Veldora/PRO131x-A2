/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro192xa3.business;

import java.util.ArrayList;
import java.util.Collections;
import pro192xa3.entity.Employee;
import pro192xa3.entity.Staff;
import pro192xa3.entity.Teacher;

/**
 *
 * @author hp
 */
public class EmployeeManagement {

    //store all staff/teacher
    ArrayList<Employee> listE;

    public EmployeeManagement() {        
        listE = new ArrayList<>();
    }

    public void addEmployee(Employee emp) {
        //add emp to listE
        //your code
    	listE.add(emp);
    }
    //search by staff/teacher's name
    public ArrayList<Employee> searchByName(String name) {
        //store all matching staff or teacher
        ArrayList<Employee> empFound = new ArrayList<>();
        //your code
        for (Employee matchEmp : listE) {
        	if (matchEmp.getFullName().toLowerCase().contains(name.toLowerCase())) {
        		empFound.add(matchEmp);
        	}
        }
        return empFound;
    }
    //search by staff/teacher's department/faculty
    public ArrayList<Employee> searchByDept(String dept) {
        ArrayList<Employee> empFound = new ArrayList<>();
        //your code
        for (Employee matchEmp : listE) {
        	if (matchEmp instanceof Staff) {
        		Staff tempEmp = (Staff) matchEmp;
        		if (tempEmp.getDepartment().toLowerCase().contains(dept.toLowerCase())) {
        			empFound.add(matchEmp);
        		}
        	} else {
        		Teacher tempEmp = (Teacher) matchEmp;
        		if (tempEmp.getFaculty().toLowerCase().contains(dept.toLowerCase())) {
        			empFound.add(matchEmp);
        		}
        	}
        }
        return empFound;
    }

    public ArrayList<Employee> listAll() {
        //sort the list of staff/teacher before return
        //your code
    	Collections.sort(listE);
        return listE;
    }

}
