package com.exam;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private String doj;
	@Column
	private String designation;
	@Column
	private double salary;
	
	public Employee() {
		super();
	}
	public Employee(int id, String name, String doj, String designation, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.doj = doj;
		this.designation = designation;
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", doj=" + doj + ", designation=" + designation + ", salary="
				+ salary + "]";
	}




}
