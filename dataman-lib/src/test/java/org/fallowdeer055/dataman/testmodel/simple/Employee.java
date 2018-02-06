package org.fallowdeer055.dataman.testmodel.simple;

public class Employee {

	@Override
	public String toString() {
		return "Employee [firstname=" + firstname + ", lastname=" + lastname + ", department=" + department + "]";
	}

	private String firstname;
	private String lastname;
	private int department;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public Employee() {
		super();
	}

	public Employee(String firstname, String lastname, int department) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + department;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (department != other.department)
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		return true;
	}

}
