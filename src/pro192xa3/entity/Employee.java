/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro192xa3.entity;

/**
 *
 * @author hp
 */
public abstract class Employee implements Comparable<Employee> {
    private String fullName;
    private double salaryRatio;//hệ số lương    
   
    private int allowance;//phụ cấp   
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getSalaryRatio() {
        return salaryRatio;
    }

    public void setSalaryRatio(double salaryRatio) {
        this.salaryRatio = salaryRatio;
    }

    public int getAllowance() {        
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    } 
    
    public abstract double getSalary();
    @Override
    public int compareTo(Employee emp){
        return this.fullName.compareTo(emp.fullName);
    }
}
