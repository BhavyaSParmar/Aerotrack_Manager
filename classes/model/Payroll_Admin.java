package model;

import java.util.ArrayList;

import org.bson.types.ObjectId;

/**
 *
 * @author aayush
 */
public class Payroll_Admin {
	private ObjectId emp_id;
    private ArrayList<String> allowances = new ArrayList<>();
    private ArrayList<String> deductions= new ArrayList<>();
    
    public ObjectId getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(ObjectId emp_id) {
		this.emp_id = emp_id;
	}
	public ArrayList<String> getAllowances() {
		return allowances;
	}
	public void setAllowances(String allowances) {
		this.allowances.add(allowances);
	}
	public ArrayList<String> getDeductions() {
		return deductions;
	}
	public void setDeductions(String deductions) {
		this.deductions.add(deductions);
	}
	
}
