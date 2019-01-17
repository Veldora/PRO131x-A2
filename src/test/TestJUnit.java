package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import pro192xa3.business.AllowanceCalulator;
import pro192xa3.business.EmployeeManagement;
import pro192xa3.entity.EDegree;
import pro192xa3.entity.EPosition;
import pro192xa3.entity.Staff;
import pro192xa3.entity.Teacher;
import pro192xa3.entity.Employee;

public class TestJUnit {

	@Test
	public void test() {
		Staff s = new Staff();
		s.setSalaryRatio(12.5);
		s.setPosition(EPosition.HEAD);
		s.setAllowance(AllowanceCalulator.calculateAllowance(s));
		s.setNoOfWorkingDay(22);
		assertEquals(s.getSalary(), 11785.0, 0.01);
		
		Teacher t = new Teacher();
		t.setSalaryRatio(20.0);
		t.setDegree(EDegree.DOCTOR);
		t.setAllowance(AllowanceCalulator.calculateAllowance(t));
		t.setTeachingHours(10);
		assertEquals(t.getSalary(), 16050.0, 0.01);
		
		EmployeeManagement empMan = new EmployeeManagement();
		try {
        	empMan.load("test_data.txt");
        } catch (FileNotFoundException ef) {
        	System.out.println("*****Load data: test_data.txt file not found.");
        } catch (IOException e) {
        	System.out.println("Can't read data from test_data.txt!");
        }
		assertEquals(empMan.searchByName("hai").get(0).getFullName(), "Pham Hai Nam");
		
		Employee deptTemp = empMan.searchByDept("it").get(0);
		if (deptTemp instanceof Staff) {
			Staff tempStaff = (Staff) deptTemp;
			assertEquals(tempStaff.getDepartment(), "IT");
		} else {
			Teacher tempTeacher = (Teacher) deptTemp;
			assertEquals(tempTeacher.getFaculty(), "IT");
		}
	}
}
