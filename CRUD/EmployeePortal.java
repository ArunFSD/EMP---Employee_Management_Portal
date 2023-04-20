package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeePortal {

	private Connection con;
	public EmployeePortal(){
		
	try {
		String dburl="jdbc:mysql://localhost:3306/ProjectCRUD";
		String dbuser="root";
		String dbpw="";
		con=DriverManager.getConnection(dburl,dbuser,dbpw);		
		}catch(SQLException e) {System.out.println("ERROR : "+e);}
	}    
	
	public void NewUser() {
		try {
			Scanner sc=new Scanner(System.in);
 	    	System.out.print("Enter the Name :");
 	    	String name=sc.next();
 	    	System.out.print("Enter the Email Id :");
 	    	String email=sc.next();
 	    	System.out.print("Enter the Password :");
 	    	String pass=sc.next();
 	    	System.out.print("Enter the Department :");
 	    	String dept=sc.next();
 	    	System.out.print("Enter the Salary :");
 	    	Double sal=sc.nextDouble();
 	    	String insert="insert into emp(Name,Email_ID,Password,Role,Salary)value(?,?,?,?,?)";
 	    	PreparedStatement insertpst=con.prepareStatement(insert);
 	    	insertpst.setString(1,name);
 	    	insertpst.setString(2,email);
 	    	insertpst.setString(3,pass);
 	    	insertpst.setString(4,dept);
 	    	insertpst.setDouble(5,sal);
			
			int insertrs=insertpst.executeUpdate();
			System.out.println((insertrs>0)?"Data Inserted Successfully":"Data Not Inserted");
			System.out.println();
			}
 	    	catch(SQLException r) {System.out.println("ERROR : "+r);}
		    catch(Exception w) {System.out.println("ERROR : "+w);}
	}
	
	public void Update() {
		try {
			Scanner sc=new Scanner(System.in);
 	    	System.out.print("Enter Email id:");
			String email1=sc.next();
			System.out.print("Enter the New Password:");
			String password=sc.next();
			
			String updatequery="update emp set Password=? where Email_ID=?";
			PreparedStatement uppst =con.prepareStatement(updatequery);
			uppst.setString(1, password);			
			uppst.setString(2, email1);			
			int uprs=uppst.executeUpdate();
			System.out.println((uprs>0)?"Updated data":"Not Updated Data");
			System.out.println();
		}
		catch(SQLException r) {System.out.println("ERROR : "+r);}
	    catch(Exception w) {System.out.println("ERROR : "+w);}
	}
	
	public void View() {
		try {
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter Email id:");
			String email2=sc.next();
			
			String select="select*from emp where Email_ID=?";
			PreparedStatement pst=con.prepareStatement(select);
			pst.setString(1, email2);
			ResultSet selectrs=pst.executeQuery();
			
			
			while(selectrs.next()) {
				int id=selectrs.getInt(1);
				String name=selectrs.getString(2);
				String email=selectrs.getString(3);
				String pass=selectrs.getString(4);
				String dept=selectrs.getString(5);
				double sal=selectrs.getDouble(6);
				System.out.println("ID"+"\t"+"Name"+"\t"+"Email"+"\t"+"Password"+"\t"+"Role"+"\t"+"Salary");
				System.out.println(id+"\t"+name+"\t"+email+"\t"+pass+"\t"+dept+"\t"+sal);
				System.out.println();
			}
 	    }
		catch(SQLException r) {System.out.println("ERROR : "+r);}
	    catch(Exception w) {System.out.println("ERROR : "+w);}
	}
	
}
