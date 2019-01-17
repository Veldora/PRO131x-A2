/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro192xa3.business;

import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import pro192xa3.entity.EDegree;
import pro192xa3.entity.EPosition;
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
        	if (matchEmp.getFullName().toLowerCase().contains(name.toLowerCase())) {	// check if employee's name contains the input
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
        		if (tempEmp.getDepartment().toLowerCase().contains(dept.toLowerCase())) {	// check if the department contains the input
        			empFound.add(matchEmp);
        		}
        	} else {
        		Teacher tempEmp = (Teacher) matchEmp;
        		if (tempEmp.getFaculty().toLowerCase().contains(dept.toLowerCase())) {	// same with faculty
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
    
    // save Employee (Staff/Teacher) to data.txt file
    public void save(Employee emp, String fileName) throws IOException {
    	File file;
    	FileWriter fr = null;
    	BufferedWriter br = null;
    	
    	try {
    		file = new File(fileName);
    		if (!file.exists()) {
    			file.createNewFile();
    		}
    		fr = new FileWriter(file, true);
    		br = new BufferedWriter(fr);
    		if (emp instanceof Staff) {
    			br.write("Staff, " + emp.toString());
    		} else {
    			br.write("Teacher, " + emp.toString());
    		}
    		br.newLine();
    	} catch (IOException e) {
    		throw e;
    	} finally {
    		br.flush();
    		br.close();
    		fr.close();
    	}
    }
    
    // load data from data.txt
    public void load(String fileName) throws IOException, FileNotFoundException {
    	BufferedReader br = null;
    	String line;
    	String[] info;
    	
    	try {
    		br = new BufferedReader(new FileReader(fileName));
    		while ((line = br.readLine()) != null) {
    			if (!line.equals("")) {
					info = line.split(", ");
					if (info[0].equals("Staff")) {
						Staff tempStaff = new Staff();
						tempStaff.setFullName(info[1]);
						tempStaff.setDepartment(info[2]);
						tempStaff.setPosition(EPosition.valueOf(info[3]));
						tempStaff.setSalaryRatio(Double.valueOf(info[4]));
						tempStaff.setAllowance(Integer.valueOf(info[5]));
						tempStaff.setNoOfWorkingDay(Integer.valueOf(info[6]));
						listE.add(tempStaff);
					} else {
						Teacher tempTeacher = new Teacher();
						tempTeacher.setFullName(info[1]);
						tempTeacher.setFaculty(info[2]);
						tempTeacher.setDegree(EDegree.valueOf(info[3]));
						tempTeacher.setSalaryRatio(Double.valueOf(info[4]));
						tempTeacher.setAllowance(Integer.valueOf(info[5]));
						tempTeacher.setTeachingHours(Integer.valueOf(info[6]));
						listE.add(tempTeacher);
					}
    			}
    		}
    	} catch (FileNotFoundException ef) {
    		throw ef;
    	} catch (IOException e) {
    		throw e;
    	} finally {
    		if (br != null) {
    			br.close();
    		}
    	}
    }

}
