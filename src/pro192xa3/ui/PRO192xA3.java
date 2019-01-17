/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro192xa3.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import jdk.nashorn.internal.parser.Lexer;
import pro192xa3.business.AllowanceCalulator;
import pro192xa3.business.EmployeeManagement;
import pro192xa3.entity.EDegree;
import pro192xa3.entity.EPosition;
import pro192xa3.entity.Employee;
import pro192xa3.entity.Staff;
import pro192xa3.entity.Teacher;

/**
 *
 * @author hp
 */
public class PRO192xA3 {

    //create an employee by inputing it's attribute values from keyboard
    static Employee createNewImployee() {
        System.out.print("Do you want to create a Staff or a Teacher (enter S for Staff, otherwise for Teacher)?");
        //accept Staff or Teacher details from keyboard        
        String choice = scan().nextLine();
        if (choice.equalsIgnoreCase("s")) {
            Staff s = new Staff();
            //input staff details
            //your code
            System.out.print("Name: ");
            s.setFullName(scan().nextLine());
            boolean salaryValid = false;
            while (!salaryValid) {
            	salaryValid = true;
	            try {
	            	System.out.print("Salary ratio: ");
	            	s.setSalaryRatio(scan().nextDouble());
	            } catch (InputMismatchException e) {
	            	System.out.println("Invalid input!");
	            	salaryValid = false;
	            }
            }
            System.out.print("Department: ");
            s.setDepartment(scan().nextLine());
            
            //validate position input
            boolean valid = true;
            do {
            	valid = true;
	            System.out.print("Position (1=HEAD; 2=VICE HEAD; 3=STAFF): ");
	            switch(scan().nextLine()) {
	            case "1":
	            	s.setPosition(EPosition.HEAD);
	            	break;
	            case "2":
	            	s.setPosition(EPosition.VICE_HEAD);
	            	break;
	            case "3":
	            	s.setPosition(EPosition.STAFF);
	            	break;
	            default:
	            	System.out.println("Invalid input!");
	            	valid = false;
	            	break;
	            }
            } while (!valid);
            boolean numValid = false;
            while (!numValid) {
            	numValid = true;
	            try {
	            	System.out.print("Number of working days: ");
	                s.setNoOfWorkingDay(scan().nextInt());
	            } catch (InputMismatchException e) {
	            	System.out.println("Invalid input!");
	            	numValid = false;
	            }
            }
            return s;

        } else {
            Teacher t = new Teacher();
            //inputs Teacher details
            //your code
            System.out.print("Name: ");
            t.setFullName(scan().nextLine());
            boolean salaryValid = false;
            while (!salaryValid) {
            	salaryValid = true;
	            try {
	            	System.out.print("Salary ratio: ");
	                t.setSalaryRatio(scan().nextDouble());
	            } catch (InputMismatchException e) {
	            	System.out.println("Invalid input!");
	            	salaryValid = false;
	            }
            }
            
            System.out.print("Faculty: ");
            t.setFaculty(scan().nextLine());
            
            //validate degree input
            boolean valid = true;
            do {
            	valid = true;
	            System.out.print("Degree (1=BACHELOR; 2=MASTER; 3=DOCTOR): ");
	            switch(scan().nextLine()) {
	            case "1":
	            	t.setDegree(EDegree.BACHELOR);;
	            	break;
	            case "2":
	            	t.setDegree(EDegree.MASTER);
	            	break;
	            case "3":
	            	t.setDegree(EDegree.DOCTOR);;
	            	break;
	            default:
	            	System.out.println("Invalid input!");
	            	valid = false;
	            	break;
	            }
            } while (!valid);
            
            boolean numValid = false;
            while (!numValid) {
            	numValid = true;
	            try {
	            	System.out.print("Number of teaching hours: ");
	                t.setTeachingHours(scan().nextInt());
	            } catch (InputMismatchException e) {
	            	System.out.println("Invalid input!");
	            	numValid = false;
	            }
            }
            
            return t;
        }

    }
    
    //return new Scanner
    private static Scanner scan() {
    	return new Scanner(System.in);
    }

    //display a list of employee
    static void display(ArrayList<Employee> listE) {
        System.out.println("Results:");
        System.out.println("Name, Fac/Dept, Deg/Pos, Sal Ratio, Allowance, T.Hours/W.Days, Salary");
        for (Employee e : listE) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        // create employee management object
        EmployeeManagement empMan = new EmployeeManagement();
        try {
        	empMan.load("data.txt");
        } catch (FileNotFoundException ef) {
        	System.out.println("*****Load data: data.txt file not found.");
        } catch (IOException e) {
        	System.out.println("Can't read data from data.txt!");
        }
        
        //menu
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("University Staff Management 1.0");
            System.out.println("\t1.Add staff");
            System.out.println("\t2.Search staff by name");
            System.out.println("\t3.Search staff by department/faculty");
            System.out.println("\t4.Display all staff");
            System.out.println("\t5.Exit");
            System.out.print("Select function (1,2,3,4 or 5): ");
            
            int choice = 0;
            boolean valid = false;
            while (!valid) {
            	valid = true;
            	try {
            		choice = scan().nextInt();
            	} catch (InputMismatchException e) {
            		System.out.print("Please enter 1, 2, 3, 4 or 5: ");
            		valid = false;
            	}
            }
            
            switch (choice) {
                case 1://add staff/teacher    
                    Employee emp = createNewImployee();
                    int allowance = AllowanceCalulator.calculateAllowance(emp);
                    emp.setAllowance(allowance);
                    empMan.addEmployee(emp);
                    try {
                    	empMan.save(emp, "data.txt");
                    } catch (IOException e) {
                    	System.out.println("Can't write data to data.txt!");
                    }
                    break;
                case 2://search by name                    
                    System.out.print("\tEnter name to search: ");
                    String name = scan().nextLine();
                    ArrayList<Employee> foundByName = empMan.searchByName(name);
                    display(foundByName);
                    break;
                case 3://search by dept
                    System.out.print("\tEnter dept/fac to search: ");
                    String dept = scan().nextLine();
                    ArrayList<Employee> foundByDept = empMan.searchByDept(dept);
                    display(foundByDept);
                    break;
                case 4://display all
                    ArrayList<Employee> listE = empMan.listAll();
                    display(listE);
                    break;
                case 5://exit
                	System.out.println("Bye!");
                    keepRunning = false;
                    break;
                default:
                	System.out.println("Please enter 1, 2, 3, 4 or 5!");
                	break;
            }
            System.out.println();
        }
    }
}
