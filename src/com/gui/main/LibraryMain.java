package com.gui.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.gui.service.LibraryService;

public class LibraryMain {
	
	final String username = "Jatin";
	final String password = "Jatin123";
	String user = "";
	String pass = "";
	static Scanner sc = new Scanner(System.in);
	public void login()throws InterruptedException {
		System.out.println("\t\t\t\t\tLIBRARY MANAGEMENT SYSTEM");
		System.out.println("\t\t\t\t\t\t\tA Smart Way to Manege your Books.");
		System.out.println("\n\t\t\t\t\tLOGIN");
		do {
			System.out.print("<------- Enter Username: ");
			user = sc.nextLine();
			System.out.print("<------- Enter Password: ");
			pass = sc.nextLine();
			
			if(!username.equals(user)) {
				System.out.println("<-------❌Invalid Username! Please Enter Valid Username.❌------->");
			}
			else if(!password.equals(pass)) {
				System.out.println("<-------❌Invalid Password! Please Enter Valid Username.❌------->");
			}
			else {
				System.out.print("<---Logging");
				for(int i =1; i<=5; i++) {
					Thread.sleep(800);
					System.out.print(".");
				}
				System.out.println("\n\t\t\t\t\t\tLogin Successful! ✔️");
				System.out.println("\t\t\t\t\t\t Welcome "+user+"🙂");
			}
		} while (!username.equals(user) || !password.equals(pass));
	}
	public static void main(String[] args) throws InterruptedException, SQLException{
		
//		System.out.println("\t\t\t\t\tLIBRARY MANAGEMENT SYSTEM");
//		System.out.println("\t\t\t\t\t\t\tA Smart Way to Manege your Books.");
//		System.out.println("\n\t\t\t\t\tLOGIN");
		LibraryMain lm = new LibraryMain();
		lm.login();
		LibraryService ls = new LibraryService();
		while(true) {
			System.out.println("<---------------------------------- Choose Operation Below ↓ -------------------------------->");
			System.out.println("\t1. Add Books ➕.\n\t2. Show Books 📖.\n\t3. Update Book 🆕.\n\t4. Remove Book ❌.\n\t5. Borrow Book 🤝/Return Book ↩️.\n\t6. Log out ❌.");
			System.out.print("<-------Enter no.:");
			int choose = Integer.parseInt(sc.nextLine());
			
			switch(choose) {
			case 1:
				System.out.println("\n<----- Enter Book Details:");
				ls.addBook();
				break;
			case 2:
				ls.show();
				break;
			case 3:
				ls.update();
				break;
			case 4:
				ls.delete();
				break;
			case 5:
				while(true) {
					
				System.out.println("<------- Choose Operation: ");
				System.out.println("<------- 1. Borrow Book. ");
				System.out.println("<------- 2. Return Book. ");
				int oper = Integer.parseInt(sc.nextLine());
				if(oper == 1) {
					ls.borrowBook();
					break;
				}
				else if(oper == 2) {
					ls.returnBook();
					break;
				}
				else {
					System.out.println("<-------❌Invalid Choice! Please Enter Valid Choice.❌------->");
				}
				}
				break;
			case 6:
				lm.login();
			}
		}
		
		
		
		
	}
}
