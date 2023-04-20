package CRUD;
import java.util.Scanner;

public class EMP {
	public static void main(String[] args){
		
		AdminPortal ap=new AdminPortal();
		EmployeePortal ep=new EmployeePortal();
		
		Scanner sc=new Scanner(System.in);
		
	while(true){
		System.out.println("<<---Welcome to Employee Management Portal--->>");
		System.out.println("Press 1: To Login as an Admin\nPress 2: To Login as an Employee\nPress 3: To Exit");
		System.out.print("---> Select the Domain : ");
		int option=sc.nextInt();
		System.out.println();
		
		if(option==1) {
			 System.out.print("Enter the Email ID : ");
	         String em=sc.next();
	         System.out.print("Enter the Password : ");
	         String pw=sc.next();	
	         ap.Login(em, pw);
	     }          
		else if(option==2) {
			while(true){
			System.out.println("Press 1: New User\nPress 2: Existing User\nPress 3: Previous Option");
			System.out.print("---> Enter the choice : ");
			int user=sc.nextInt();
			System.out.println();
			if(user==1) {
				ep.NewUser();
			}
			
			else if(user==2){
				System.out.println("Press 1: Update your password\nPress 2: View your details\nPress 3: Previous Option");
				System.out.print("---> Enter the choice : ");
				int uv=sc.nextInt();
				System.out.println();
				if(uv==1) {
					ep.Update();
				}else if(uv==2) {
					ep.View();
				}
				else if(uv==3) {
					System.out.println();
					
				}
				else {
					System.out.println("Invalid Option");
					 System.out.println();		
				}
			}
			
			else if(user==3) {
				System.out.println();
				break;
			}
			else {
				System.out.println("Invalid Option");
				 System.out.println();			
			}
		}
		}
		else if(option==3) {
			System.out.println();
			break;
		}
		else {
			 System.out.println("Invalid Option");
			 System.out.println();
		}
	  }                                   
	}                                            
}                                                

