package com.uiFramework.KisanForum.KisanNetWeb.helper.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDBQuery {

		
	public String getOTP(String mobileNumber) throws NumberFormatException, SQLException {
		int id = 0;
		String updatedMobile = "%"+mobileNumber+"%";
		System.out.println("Updated number is : " + updatedMobile);
		try {
		String dbQuery = "select * from profiles_verifymobiles where mobile like '"+ updatedMobile + "' AND code_verified_date is NULL order by code_sent_date desc;";
		ResultSet result = DataBaseHelper.getResultSet(dbQuery);
		result.next();
		id = Integer.parseInt(result.getString("code"));
		}
		catch (Exception e) {
			System.out.println("OTP is not generated");
		}
		return Integer.toString(id);
	}
	
	public List<Employee> getEmployee() throws SQLException{
		List<Employee> data = new ArrayList<Employee>();
		String dbQuery = "SELECT * FROM person.employee";
		ResultSet result = DataBaseHelper.getResultSet(dbQuery);
		while (result.next()) {
			Employee emp = new  Employee();
			emp.setEmpId(Integer.parseInt(result.getString("idemployee")));
			emp.setSalary(Integer.parseInt(result.getString("salary")));
			emp.setName(result.getString("name"));
			emp.setAddress(result.getString("address"));
			
			data.add(emp);
		}
		
		return data;
	}

	public static void main(String[] args) throws NumberFormatException, SQLException {
		ApplicationDBQuery applicationDBQuery = new ApplicationDBQuery();
		String otp = applicationDBQuery.getOTP("4567800005");
		//String otp1 = Integer.toString(otp);
		System.out.println("Verification code is : " + otp);
		/*List<Employee> listOfData = applicationDBQuery.getEmployee();
		for(Employee data: listOfData){
			System.out.println("empId is :"+data.getEmpId()+" emp salary is: "+data.getSalary()+" emp name is: "+data.getName());
		}*/
	}
}
