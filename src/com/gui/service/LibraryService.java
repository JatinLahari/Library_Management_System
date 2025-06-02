package com.gui.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;
import com.gui.model.LibraryModel;
import com.gui.dao.LibraryDAO;
public class LibraryService {
	Scanner sc = new Scanner(System.in);
	LibraryDAO dao = new LibraryDAO();
	public void addBook() throws SQLException{
		String more = null;
		do {
		int id = 0;
		while(true) {
			try {
				System.out.print("Enter BookID: ");
				 id = Integer.parseInt(sc.nextLine());
				break;
			}catch(ArithmeticException s) {
				System.out.println("<------- ❌Invalid Book ID! Book ID should be digits. ------->");
			}
		}
		String title = null;
		while(true) {
				System.out.print("Enter Book Title: ");
				 title = sc.nextLine();
				if(title.isEmpty()) {
					System.out.println("<------- ❌Invalid Book Title! A book must have a Title. ------->");
				}
				else {
					break;					
				}
		}
		String isbn = null;
		while(true) {
			System.out.print("Enter Book ISBN no.: ");
			 isbn = sc.nextLine();
			if(isbn.isEmpty()) {
				System.out.println("<------- ❌Invalid Book ISBN no.! Please Enter Valid ISBN number. ------->");
			}
			else {
				break;					
			}
		}
		String author = null;
		while(true) {
			System.out.print("Enter Author Name: ");
			 author = sc.nextLine();
			int a = 0;
			for(int i =0; i<author.length(); i++) {
				if(author.charAt(i) >= '0' && author.charAt(i) <= '9') {
					a++;
				}
			}
			if(author.isEmpty() || a > 0) {
				System.out.println("<------- ❌Invalid Author Name! Please Enter a valid Author Name. ------->");
			}
			else {
				break;					
			}
		}
		String contact = null;
		while(true) {
			System.out.print("Enter Author Contact no.: ");
			 contact = sc.nextLine();
			int c1 = 0;
			int c2 = 0;
			for (int i=0;i<contact.length();i++) {
				if(contact.charAt(i)>='A' && contact.charAt(i)<='Z')
				{
					c1++;
				}
				if(contact.charAt(i)>='a' && contact.charAt(i)<='z')
				{
					c2++;
				}
			}
			if(contact.isEmpty()) {
				System.out.println("<------- ❌Invalid Contact no.! Author Contact No. is must. ------->");
			}
			else if(c1 > 0 || c2 > 0) {
				System.out.println("<------- ❌Invalid Contact no.! Please Enter a Valid Contact no. ------->");
			}
			else if(contact.length() > 10 || contact.length() < 10) {
				System.out.println("<------- ❌Invalid Contact no.! Please Enter 10 digit Contact no. ------->");
			}
			else if(!contact.isEmpty() && c1 == 0 && c2 == 0 && contact.length() == 10) {
				break;					
			}
	}
		String address = null;
		while(true) {
			System.out.print("Enter Author Address: ");
			 address = sc.nextLine();
			if(address.isEmpty()) {
				System.out.println("<------- ❌Invalid Author Address! Please Enter a valid Author Name. ------->");
			}
			else {
				break;					
			}
		}
		int avail = 0;
		while(true) {
			try {				
			System.out.print("Enter Available no of Copies: ");
			avail = Integer.parseInt(sc.nextLine());
			break;
			}catch(ArithmeticException s) {
				System.out.println("<------- ❌Invalid Available Copies! Available Copies must be in digits. ------->");				
			}	
		}
		LibraryModel lm = new LibraryModel(id,title,isbn,avail,author,contact,address);
		dao.insert(lm);
		
		System.out.println("\n<------- Book Added Successfully! ✔️ ------->");
		System.out.println("\n<------- More Books?");
		more = sc.nextLine();
		}while(more.equalsIgnoreCase("yes"));
	}
	
	// Show Books
	public void show() throws SQLException{
		System.out.println("\n<---------------------------------- Choose Operation Below ↓ -------------------------------->");
		System.out.println("\t1. Show All Books.\n\t2. Show By Book ID.\n\t3. Show by Book Title.\n\t4. Show by ISBN Number.\n\t5. Show by Author Name.");
		System.out.print("<------- Enter no.: ");
		int show = Integer.parseInt(sc.nextLine());
		switch (show) {
		case 1:
			List<LibraryModel> lt = dao.showAll();
			if(lt.isEmpty()) {
				System.out.println("No Books Are Available.");
			}
			else {
				System.out.println("\n<------- List of the Available Books: ");
			for(LibraryModel l : lt) {
				System.out.println();
				System.out.println("\t | Book ID: "+l.getBookId());
				System.out.println("\t | Book Title: "+l.getBookTitle());
				System.out.println("\t | Book ISBN no.: "+l.getISBN());
				System.out.println("\t | Book Author: "+l.getAuthor());
				System.out.println("\t | Author Contact no.: "+l.getContact());
				System.out.println("\t | Author Address: "+l.getAddress());
				System.out.println("\t | Available Quantity: "+l.getAvailableCopies());
			}
			}
			break;
		case 2:
			System.out.print("\n<------- Enter BookID: ");
			int i = Integer.parseInt(sc.nextLine());
			List<LibraryModel> lt2 = dao.showById(i);
			if(lt2.isEmpty()) {
				System.out.println("No Books Are Available.");
			}
			else {
				System.out.println("\n<------- List of the Available Books: ");
			for(LibraryModel l : lt2) {
				System.out.println();
				System.out.println("\t | Book ID: "+l.getBookId());
				System.out.println("\t | Book Title: "+l.getBookTitle());
				System.out.println("\t | Book ISBN no.: "+l.getISBN());
				System.out.println("\t | Book Author: "+l.getAuthor());
				System.out.println("\t | Author Contact no.: "+l.getContact());
				System.out.println("\t | Author Address: "+l.getAddress());
				System.out.println("\t | Available Quantity: "+l.getAvailableCopies());
			}
			}
			break;
		case 3:
			System.out.print("\n<------- Enter Book Title: ");
			String ttl = sc.nextLine();
			List<LibraryModel> lt3 = dao.showByTitle(ttl);
			if(lt3.isEmpty()) {
				System.out.println("No Books Are Available.");
			}
			else {
				System.out.println("\n<------- List of the Available Books: ");
			for(LibraryModel l : lt3) {
				System.out.println();
				System.out.println("\t | Book ID: "+l.getBookId());
				System.out.println("\t | Book Title: "+l.getBookTitle());
				System.out.println("\t | Book ISBN no.: "+l.getISBN());
				System.out.println("\t | Book Author: "+l.getAuthor());
				System.out.println("\t | Author Contact no.: "+l.getContact());
				System.out.println("\t | Author Address: "+l.getAddress());
				System.out.println("\t | Available Quantity: "+l.getAvailableCopies());
			}
			}
			break;
		case 4:
			System.out.print("\n<------- Enter Book ISBN: ");
			String isbn = sc.nextLine();
			List<LibraryModel> lt4 = dao.showByIsbn(isbn);
			if(lt4.isEmpty()) {
				System.out.println("No Books Are Available.");
			}
			else {
				System.out.println("\n<------- List of the Available Books: ");
			for(LibraryModel l : lt4) {
				System.out.println();
				System.out.println("\t | Book ID: "+l.getBookId());
				System.out.println("\t | Book Title: "+l.getBookTitle());
				System.out.println("\t | Book ISBN no.: "+l.getISBN());
				System.out.println("\t | Book Author: "+l.getAuthor());
				System.out.println("\t | Author Contact no.: "+l.getContact());
				System.out.println("\t | Author Address: "+l.getAddress());
				System.out.println("\t | Available Quantity: "+l.getAvailableCopies());
			}
			}
			break;
		case 5:
			System.out.print("\n<------- Enter Author Name: ");
			String auth = sc.nextLine();
			List<LibraryModel> lt5 = dao.showByAuthor(auth);
			if(lt5.isEmpty()) {
				System.out.println("No Books Are Available.");
			}
			else {
				System.out.println("\n<------- List of the Available Books: ");
			for(LibraryModel l : lt5) {
				System.out.println();
				System.out.println("\t | Book ID: "+l.getBookId());
				System.out.println("\t | Book Title: "+l.getBookTitle());
				System.out.println("\t | Book ISBN no.: "+l.getISBN());
				System.out.println("\t | Book Author: "+l.getAuthor());
				System.out.println("\t | Author Contact no.: "+l.getContact());
				System.out.println("\t | Author Address: "+l.getAddress());
				System.out.println("\t | Available Quantity: "+l.getAvailableCopies());
			}
			}
			break;
		default: System.out.println("<------- ❌Invalid Choice! ------->");
			break;
		}
	}
	
	
	// Book update
	public void update() throws SQLException{
		System.out.println("\n<---------------------------------- Choose Operation Below ↓ -------------------------------->");
		System.out.println("\n\t1. Update Book Title using Book ID.\n\t2. Update Author Name using Book ID.\n\t3. Update ISBN no using BookID.\n\t4. Update Available no. of Copies using BookID.\n\t5. Update Contact Number using BookID.");
		System.out.print("<------- Enter no.: ");
		int update = Integer.parseInt(sc.nextLine());
		
		// Book title update
		if(update == 1) {
			System.out.print("<------- Enter BookID: ");
			int id = Integer.parseInt(sc.nextLine());
			System.out.print("<------- Enter new Book Title: ");
			String ttl = sc.nextLine();
			ResultSet rs = dao.updateTitle(id, ttl);
			if(rs.next()) {
				System.out.println("<------- ✅Updated Book Title: "+rs.getString(1));				
			}
		}
		else if(update == 2) {
			System.out.print("<------- Enter BookID: ");
			int id = Integer.parseInt(sc.nextLine());
			System.out.print("<------- Enter new Author Name: ");
			String ttl = sc.nextLine();
			ResultSet rs = dao.updateAuthor(id, ttl);
			if(rs.next()) {
				System.out.println("<------- ✅Updated Author Name: "+rs.getString(1));				
			}
		}
		else if(update == 3) {
			System.out.print("<------- Enter BookID: ");
			int id = Integer.parseInt(sc.nextLine());
			System.out.print("<------- Enter new ISBN no.: ");
			String isbn = sc.nextLine();
			ResultSet rs = dao.updateIsbn(id, isbn);
			if(rs.next()) {
				System.out.println("<------- ✅Updated ISBN no.: "+rs.getString(1));				
			}
		}
		else if(update == 4) {
			System.out.print("<------- Enter BookID: ");
			int id = Integer.parseInt(sc.nextLine());
			System.out.print("<------- Enter new Available quantity no.: ");
			int avail = Integer.parseInt(sc.nextLine());
			ResultSet rs = dao.updateAvailable(id, avail);
			if(rs.next()) {
				System.out.println("<------- ✅Updated Available Quantity: "+rs.getString(1));				
			}
		}
		else if(update == 5) {
			System.out.print("<------- Enter BookID: ");
			int id = Integer.parseInt(sc.nextLine());
			String contact;
			while(true) {
			System.out.print("<------- Enter new Contact no.: ");
			 contact = sc.nextLine();
			int c1 = 0;
			int c2 = 0;
			for (int i=0;i<contact.length();i++) {
				if(contact.charAt(i)>='A' && contact.charAt(i)<='Z')
				{
					c1++;
				}
				if(contact.charAt(i)>='a' && contact.charAt(i)<='z')
				{
					c2++;
				}
			}
			if(contact.isEmpty()) {
				System.out.println("<------- ❌Invalid Contact no.! Author Contact No. is must. ------->");
			}
			else if(c1 > 0 || c2 > 0) {
				System.out.println("<------- ❌Invalid Contact no.! Please Enter a Valid Contact no. ------->");
			}
			else if(contact.length() > 10 || contact.length() < 10) {
				System.out.println("<------- ❌Invalid Contact no.! Please Enter 10 digit Contact no. ------->");
			}
			else if(!contact.isEmpty() && c1 == 0 && c2 == 0 && contact.length() == 10) {
				break;					
			}
		}
			ResultSet rs = dao.updateContact(id, contact);
			if(rs.next()) {
				System.out.println("<------- ✅Updated Contact no.: "+rs.getString(1));				
			}
		}
	}
	
	// book delete 
	public void delete() throws SQLException {
		System.out.println("\n<---------------------------------- Choose Operation Below ↓ -------------------------------->");
		System.out.println("\t1. Remove Book by Book ID.\n\t2. Remove Book By Book Title.\n\t3. Remove Book By ISBN no.");
		System.out.print("<-------Enter no.:");
		int delete = Integer.parseInt(sc.nextLine());
		
		if(delete == 1) {
			System.out.print("\n<------- Enter BookID: ");
			int id = Integer.parseInt(sc.nextLine());
			dao.deleteId(id);
		}
		else if(delete == 2) {
			System.out.print("\n<------- Enter Book Title: ");
			String title = sc.nextLine();
			dao.deleteTitle(title);
		}
		else if(delete == 3) {
			System.out.print("\n<------- Enter Book ISBN: ");
			String isbn = sc.nextLine();
			dao.deleteIsbn(isbn);
		}
	}
	
	public void borrowBook() throws SQLException {
		System.out.print("<------- Enter Book Title: ");
		String ttl = sc.nextLine();
		System.out.print("<------- Enter Borrower Name: ");
		String name = sc.nextLine();
		dao.borrow(ttl, name);
	}
	
	public void returnBook() throws SQLException{
		System.out.print("<------- Enter Book Title: ");
		String ttl = sc.nextLine();
		System.out.print("<------- Enter Borrower Name: ");
		String name = sc.nextLine();	
		dao.returnBook(ttl, name);
	}
	
	
	
	
	
	
	
}
