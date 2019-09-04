import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DatabaseConnection {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter choice");
		int input;

		do {

			System.out.println("----------------Choose----------------------");
			System.out.println("Enter 1 for inserting in Employee table");
			System.out.println("Enter 2 for deleting record from employee table");
			System.out.println("Enter 3 for updating salary of the employee");
			System.out.println("Enter 4 for updating name of employee");
			System.out.println("Enter 5 for creating new table");
			System.out.println("Enter 10 for exit");

			input = sc.nextInt();

			switch (input) {
			case 1: {
				System.out.println("Enter the number of records you want to insert");
						
				int numberOfRecords = sc.nextInt();

				ArrayList<Employee> alist = new ArrayList<>();

				for (int i = 0; i < numberOfRecords; i++) {
					System.out.println("Enter "+(i+1)+" employee details");
					System.out.println("Enter employee no");
					int eno = sc.nextInt();
					System.out.println("Enter employee name");
					String ename = sc.next();
					System.out.println("Enter employee salary");
					int sal = sc.nextInt();

					Employee e = new Employee(eno, ename, sal);
					alist.add(e);

				}

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/wp", "root", "root");

					for (int i = 0; i < numberOfRecords; i++) {
						PreparedStatement psmt = (PreparedStatement) con.prepareStatement("insert into emp values(?,?,?)");
								
						psmt.setInt(1, alist.get(i).getEno());
						psmt.setString(2, alist.get(i).getEname());
						psmt.setInt(3, alist.get(i).getSal());

						int result = psmt.executeUpdate();

						System.out.println("Record insert successfully");

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				break;
			}
			
			case 2:
			{
				System.out.println("Enter eno of employee");
				int enumber=sc.nextInt();
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/wp","root","root");
				Statement smt=(Statement) con.createStatement();
				
				int result=smt.executeUpdate("delete from emp where eno="+enumber);
				if(result==1)
					System.out.println("Employee record with eno: "+enumber+" deleted");
				else
					System.out.println("Employee not found");
					
				break;
			}
			
			case 3:
			{
				System.out.println("Enter eno of employee");
				int enumber=sc.nextInt();
				System.out.println("Enter new Salary");
				int salary=sc.nextInt();
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/wp","root","root");
				Statement smt=(Statement) con.createStatement();
				
				int result=smt.executeUpdate("update emp set sal="+salary+" where eno="+enumber);
				
				if(result==1)
						System.out.println("Salary updated successfully");
				else
						System.out.println("Employee not found");
				break;
			}
			
			case 4:
			{
				System.out.println("Enter eno of employee");
				int enumber=sc.nextInt();
				System.out.println("Enter new name of employee");
				String name=sc.next();
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/wp","root","root");
				Statement smt=(Statement) con.createStatement();
				
				int result=smt.executeUpdate("update emp set ename='"+name+"' where eno="+enumber);
				
				if(result==1)
						System.out.println("Employee namne updated successfully");
				else
						System.out.println("Employee not found");
				
				break;
			}
			
			case 5:
			{
				System.out.println("Enter table name");
				String tableName=sc.next();
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/wp","root","root");
				Statement smt=(Statement) con.createStatement();
				
				try
				{
				int result=smt.executeUpdate("create table "+tableName+"(no int,name varchar(20))");
				}catch(Exception e)
				{
					System.out.println("Table already exists");
				}
				
				
				break;
			}
			default:
			{
				System.out.println("Please enter correct values");
			}
			}
		} while (input != 10);
	}

}

class Employee {
	private int eno;
	private String ename;
	private int sal;

	public Employee(int eno, String ename, int sal) {
		super();
		this.eno = eno;
		this.ename = ename;
		this.sal = sal;
	}

	public int getEno() {
		return eno;
	}

	public void setEno(int eno) {
		this.eno = eno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	@Override
	public String toString() {
		return "Employee [eno=" + eno + ", ename=" + ename + ", sal=" + sal
				+ "]";
	}

}