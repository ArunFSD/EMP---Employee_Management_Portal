package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminPortal {
	private Connection con;
	public AdminPortal(){
		
	try {
		String dburl="jdbc:mysql://localhost:3306/ProjectCRUD";
		String dbuser="root";
		String dbpw="";
		con=DriverManager.getConnection(dburl,dbuser,dbpw);		
		}catch(SQLException e) {System.out.println("ERROR : "+e);}
	}                                                                   //connection.close
	
	public void Login(String em,String pw) {
	try {
		Scanner sc=new Scanner(System.in);
		String query="select Email_ID,Password from emp where Identification=101";
	    PreparedStatement pst =con.prepareStatement(query);
	    ResultSet rs=pst.executeQuery();
	    String email="";
    	String pass="";
	    while(rs.next()) {
	    	email=rs.getString(1);
	    	pass=rs.getString(2);
	    }
		String query1="select Email_ID,Password from emp where Identification=102";
		PreparedStatement pst1 =con.prepareStatement(query1);
		ResultSet rs1=pst1.executeQuery();
		String email1="";
	    String pass1="";
		while(rs1.next()) {
		    email1=rs1.getString(1);
		    pass1=rs1.getString(2);
	    }
		    if( email.equals(em) && pass.equals(pw) || email1.equals(em) && pass1.equals(pw) ) {
		    	System.out.println("<<---Login Success--->>");
		    	System.out.println();
		    	  
		    	while(true) { 
		    	    System.out.println("Welcome to Admin Portal");
			    	System.out.println("Press 1: To View the Entire database\nPress 2: To Insert a data\nPress 3: To Update Password\nPress 4: To Delete a data\nPress 5: Back to Employee Management Portal");
			    	System.out.print("Choose The Option :");
			    	int ADop=sc.nextInt();
			    	System.out.println();
			    	if(ADop==1) {
			    		View();
			    	}
			    	else if(ADop==2) {
			    		Insert();
			    	}
			    	else if(ADop==3) {
			    		Update();
			    	}
			    	else if(ADop==4) {
			    		Delete();
			    	}
			    	else if(ADop==5) {
						System.out.println();
						break;
					}
					else {
						throw new Exception("Invalid Option");		
					}
			    }
		    }
		    else {
		    	System.out.println("<<---Login Failed Invalid User--->>");
		    	System.out.println();
		    }
	}
	catch(SQLException r) {System.out.println("ERROR : "+r);}
    catch(Exception w) {System.out.println("ERROR : "+w);}
	}                                                             

	public void View() {
		try {
			String select="select*from emp";
			PreparedStatement selectpst=con.prepareStatement(select);
			ResultSet selectrs=selectpst.executeQuery();
			while(selectrs.next()) {
				int Eid=selectrs.getInt(1);
				String Ename=selectrs.getString(2);
				String Eemail=selectrs.getString(3);
				String Epass=selectrs.getString(4);
				String Edept=selectrs.getString(5);
				double Esal=selectrs.getDouble(6);
				System.out.println("ID"+"\t"+"Name"+"\t"+"Email"+"\t"+"Password"+"\t"+"Role"+"\t"+"Salary");
				System.out.println(Eid+"\t"+Ename+"\t"+Eemail+"\t"+Epass+"\t"+Edept+"\t"+Esal);
				System.out.println();
			}
 	    }
		catch(SQLException r) {System.out.println("ERROR : "+r);}
	    catch(Exception w) {System.out.println("ERROR : "+w);}
	}
		
	
	public void Insert() {
		try {
			Scanner sc=new Scanner(System.in);
 	    	System.out.print("Enter the Name :");
 	    	String upname=sc.next();
 	    	System.out.print("Enter the Email Id :");
 	    	String upemail=sc.next();
 	    	System.out.print("Enter the Password :");
 	    	String uppw=sc.next();
 	    	System.out.print("Enter the Role :");
 	    	String updept=sc.next();
 	    	System.out.print("Enter the Salary :");
 	    	Double upsal=sc.nextDouble();
 	    	String insert="insert into emp(Name,Email_ID,Password,Role,Salary)value(?,?,?,?,?)";
 	    	PreparedStatement insertpst=con.prepareStatement(insert);
 	    	insertpst.setString(1,upname);
 	    	insertpst.setString(2,upemail);
 	    	insertpst.setString(3,uppw);
 	    	insertpst.setString(4,updept);
 	    	insertpst.setDouble(5,upsal);
			
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
			String email2=sc.next();
			System.out.print("Enter the New Password:");
			String password=sc.next();
			
			String updatequery="update emp set Password=? where Email_ID=?";
			PreparedStatement uppst =con.prepareStatement(updatequery);
			uppst.setString(1, password);			
			uppst.setString(2, email2);			
			int uprs=uppst.executeUpdate();
			System.out.println((uprs>0)?"Password Updated":"Password not changed");
			System.out.println();
		}
		catch(SQLException r) {System.out.println("ERROR : "+r);}
	    catch(Exception w) {System.out.println("ERROR : "+w);}
	}

	public void Delete() {
		try {
			Scanner sc=new Scanner(System.in);
 	    	System.out.print("Enter Email id to delete the data :");
			String email3=sc.next();
			
			String deletequery="delete from emp where Email_ID=?";
			PreparedStatement depst =con.prepareStatement(deletequery);			
			depst.setString(1, email3);			
			int ders=depst.executeUpdate();
			System.out.println((ders>0)?"Data has been Deleted":"Data not Deleted");
			System.out.println();
		}
		catch(SQLException r) {System.out.println("ERROR : "+r);}
	    catch(Exception w) {System.out.println("ERROR : "+w);}
	}
}
