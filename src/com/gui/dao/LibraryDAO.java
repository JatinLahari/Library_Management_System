package com.gui.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gui.model.LibraryModel;

public class LibraryDAO {

	private Connection getConnect() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Jatin.8889");
	}

	Scanner sc = new Scanner(System.in);
	PreparedStatement ps = null;

	// inserting book 
	public void insert(LibraryModel lm) throws SQLException {

		Connection con = getConnect();
		String insert = "INSERT INTO Books(BookID, Book_Title, ISBN_Number, Available_Copies) VALUES(?,?,?,?)";
		ps = con.prepareStatement(insert);
		ps.setInt(1, lm.getBookId());
		ps.setString(2, lm.getBookTitle());
		ps.setString(3, lm.getISBN());
		ps.setInt(4, lm.getAvailableCopies());
		ps.executeUpdate();

		String insert2 = "INSERT INTO Authors(BookID, Author_Name, Contact_Number, Address) VALUES(?,?,?,?)";
		ps = con.prepareStatement(insert2);
		ps.setInt(1, lm.getBookId());
		ps.setString(2, lm.getAuthor());
		ps.setString(3, lm.getContact());
		ps.setString(4, lm.getAddress());
		ps.executeUpdate();
	}

	// listing all book
	public List<LibraryModel> showAll() throws SQLException {
		List<LibraryModel> l = new ArrayList<>();
		Connection con = getConnect();

		String all = "SELECT Books.BookID, Books.Book_Title, Books.ISBN_Number, Books.Available_Copies, Authors.Author_Name, Authors.Contact_Number, Authors.Address from Books  JOIN Authors ON Books.BookID = Authors.BookID";
		ps = con.prepareStatement(all);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("BookID");
			String tt = rs.getString("Book_Title");
			String isbn = rs.getString("ISBN_Number");
			int avail = rs.getInt("Available_Copies");
			String auth = rs.getString("Author_Name");
			String cont = rs.getString("Contact_Number");
			String add = rs.getString("Address");
			LibraryModel lm = new LibraryModel(id, tt, isbn, avail, auth, cont, add);
			l.add(lm);
		}
		return l;
	}

	// listing by book Id
	public List<LibraryModel> showById(int i) throws SQLException {
		List<LibraryModel> l = new ArrayList<>();
		Connection con = getConnect();
		String byid = "SELECT Books.BookID, Books.Book_Title, Books.ISBN_Number, Books.Available_Copies, Authors.Author_Name, Authors.Contact_Number, Authors.Address from Books  JOIN Authors ON Books.BookID = Authors.BookID WHERE Books.BookID = ?";
		ps = con.prepareStatement(byid);
		ps.setInt(1, i);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("BookID");
			String tt = rs.getString("Book_Title");
			String isbn = rs.getString("ISBN_Number");
			int avail = rs.getInt("Available_Copies");
			String auth = rs.getString("Author_Name");
			String cont = rs.getString("Contact_Number");
			String add = rs.getString("Address");
			LibraryModel lm = new LibraryModel(id, tt, isbn, avail, auth, cont, add);
			l.add(lm);
		}
		return l;
	}

	// listing by book title
	public List<LibraryModel> showByTitle(String ttl) throws SQLException {
		List<LibraryModel> l = new ArrayList<>();
		Connection con = getConnect();
		String bytt = "SELECT Books.BookID, Books.Book_Title, Books.ISBN_Number, Books.Available_Copies, Authors.Author_Name, Authors.Contact_Number, Authors.Address from Books  JOIN Authors ON Books.BookID = Authors.BookID WHERE Books.Book_Title = ?";
		ps = con.prepareStatement(bytt);
		ps.setString(1, ttl);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("BookID");
			String tt = rs.getString("Book_Title");
			String isbn = rs.getString("ISBN_Number");
			int avail = rs.getInt("Available_Copies");
			String auth = rs.getString("Author_Name");
			String cont = rs.getString("Contact_Number");
			String add = rs.getString("Address");
			LibraryModel lm = new LibraryModel(id, tt, isbn, avail, auth, cont, add);
			l.add(lm);
		}
		return l;
	}

	// listing by book isbn
	public List<LibraryModel> showByIsbn(String is) throws SQLException {
		List<LibraryModel> l = new ArrayList<>();
		Connection con = getConnect();
		String bytt = "SELECT Books.BookID, Books.Book_Title, Books.ISBN_Number, Books.Available_Copies, Authors.Author_Name, Authors.Contact_Number, Authors.Address from Books  JOIN Authors ON Books.BookID = Authors.BookID WHERE Books.ISBN_Number = ?";
		ps = con.prepareStatement(bytt);
		ps.setString(1, is);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("BookID");
			String tt = rs.getString("Book_Title");
			String isbn = rs.getString("ISBN_Number");
			int avail = rs.getInt("Available_Copies");
			String auth = rs.getString("Author_Name");
			String cont = rs.getString("Contact_Number");
			String add = rs.getString("Address");
			LibraryModel lm = new LibraryModel(id, tt, isbn, avail, auth, cont, add);
			l.add(lm);
		}
		return l;
	}

	// listing by book author
	public List<LibraryModel> showByAuthor(String author) throws SQLException {
		List<LibraryModel> l = new ArrayList<>();
		Connection con = getConnect();
		String aut = "SELECT Books.BookID, Books.Book_Title, Books.ISBN_Number, Books.Available_Copies, Authors.Author_Name, Author.Contact_Number, Authors.Address FROM Books JOIN Authors ON Books.BookID = Authors.BookID WHERE Authors.Author_Name = ?";
		ps = con.prepareStatement(aut);
		ps.setString(1, author);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("BookID");
			String ttl = rs.getString("Book_Title");
			String isbn = rs.getString("ISBN_Number");
			int avail = rs.getInt("Available_Copies");
			String auth = rs.getString("Author_Name");
			String cont = rs.getString("Contact_Number");
			String add = rs.getString("Address");

			LibraryModel lm = new LibraryModel(id, ttl, isbn, avail, auth, cont, add);
			l.add(lm);
		}
		return l;
	}

	// update book title
	public ResultSet updateTitle(int id, String ttl) throws SQLException {
		Connection con = getConnect();
		String c1 = "SELECT BookID from Books where BookID = ?";
		ps = con.prepareStatement(c1);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ResultSet rs2 = null;
		if (!rs.next()) {
			System.out.println("<------- ❌No Book Available of this BookID.------->");
		} else {
			String upd = "UPDATE Books Set Book_Title = ? WHERE BookID = ?";
			ps = con.prepareStatement(upd);
			ps.setString(1, ttl);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("<------- Book Title Updated Successfully! ✔️------->");
			String g1 = "SELECT Book_Title from Books where BookID = ?";
			ps = con.prepareStatement(g1);
			ps.setInt(1, id);
			rs2 = ps.executeQuery();
		}
		return rs2;

	}

	// update author name
	public ResultSet updateAuthor(int id, String auth) throws SQLException {
		Connection con = getConnect();
		String c1 = "SELECT BookID from Authors where BookID = ?";
		ps = con.prepareStatement(c1);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ResultSet rs2 = null;
		if (!rs.next()) {
			System.out.println("<------- ❌No Book Available of this BookID.------->");
		} else {
			String upd = "UPDATE Authors Set Author_Name = ? WHERE BookID = ?";
			ps = con.prepareStatement(upd);
			ps.setString(1, auth);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("<------- Author Name Updated Successfully! ✔️------->");
			String g1 = "SELECT Author_Name from Authors where BookID = ?";
			ps = con.prepareStatement(g1);
			ps.setInt(1, id);
			rs2 = ps.executeQuery();
		}
		return rs2;
	}
	
	// update isbn no
	public ResultSet updateIsbn(int id, String isb) throws SQLException {
		Connection con = getConnect();
		String c1 = "SELECT BookID from Books where BookID = ?";
		ps = con.prepareStatement(c1);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ResultSet rs2 = null;
		if (!rs.next()) {
			System.out.println("<------- ❌No Book Available of this BookID.------->");
		} else {
			String upd = "UPDATE Authors Set Author_Name = ? WHERE BookID = ?";
			ps = con.prepareStatement(upd);
			ps.setString(1, isb);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("<------- Author Name Updated Successfully! ✔️------->");
			String g1 = "SELECT ISBN_Number from Books where BookID = ?";
			ps = con.prepareStatement(g1);
			ps.setInt(1, id);
			rs2 = ps.executeQuery();
		}
		return rs2;
	}
	
	// update available quantity
	public ResultSet updateAvailable(int id, int avail) throws SQLException {
		Connection con = getConnect();
		String c1 = "SELECT BookID from Books where BookID = ?";
		ps = con.prepareStatement(c1);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ResultSet rs2 = null;
		if (!rs.next()) {
			System.out.println("<------- ❌No Book Available of this BookID.------->");
		} else {
			String upd = "UPDATE Books Set Available_Copies = ? WHERE BookID = ?";
			ps = con.prepareStatement(upd);
			ps.setInt(1, avail);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("<------- Available Quantity Updated Successfully! ✔️------->");
			String g1 = "SELECT Available_Copies from Books where BookID = ?";
			ps = con.prepareStatement(g1);
			ps.setInt(1, id);
			rs2 = ps.executeQuery();
		}
		return rs2;
	}

	// update contact no
	public ResultSet updateContact(int id, String cont) throws SQLException {
		Connection con = getConnect();
		String c1 = "SELECT BookID from Books where BookID = ?";
		ps = con.prepareStatement(c1);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ResultSet rs2 = null;
		if (!rs.next()) {
			System.out.println("<------- ❌No Book Available of this BookID.------->");
		} else {
			String upd = "UPDATE Authors Set Contact_Number = ? WHERE BookID = ?";
			ps = con.prepareStatement(upd);
			ps.setString(1, cont);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("<------- Contact no. Updated Successfully! ✔️------->");
			String g1 = "SELECT Contact_Number from Authors where BookID = ?";
			ps = con.prepareStatement(g1);
			ps.setInt(1, id);
			rs2 = ps.executeQuery();
		}
		return rs2;
	}

	// delete by ID
	public void deleteId(int id) throws SQLException {
		Connection con = getConnect();
		String c1 = "SELECT BookID FROM Books WHERE BookID = ?";
		ps = con.prepareStatement(c1);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			System.out.println("<------- ❌No Matches Found! ❌ ------->");
		} else {
			String del = "DELETE FROM Books where BookID = ?";
			ps = con.prepareStatement(del);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("<-------- Book Removed Successfully! ✔️ ------->");
		}
	}

	// delete by title
	public void deleteTitle(String ttl) throws SQLException {
		Connection con = getConnect();
		String c2 = "SELECT Book_Title FROM Books WHERE Book_Title = ?";
		ps = con.prepareStatement(c2);
		ps.setString(1, ttl);
		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			System.out.println("<------- ❌No Matches Found! ❌ ------->");
		} else {
			String del = "DELETE FROM Books where Book_Title = ?";
			ps = con.prepareStatement(del);
			ps.setString(1, ttl);
			ps.executeUpdate();
			System.out.println("<-------- Book Removed Successfully! ✔️ ------->");
		}
	}

	// delete by isbn
	public void deleteIsbn(String is) throws SQLException {
		Connection con = getConnect();
		String c3 = "SELECT ISBN_Number FROM Books WHERE ISBN_Number = ?";
		ps = con.prepareStatement(c3);
		ps.setString(1, is);
		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			System.out.println("<------- ❌No Matches Found! ❌ ------->");
		} else {
			String del = "DELETE FROM Books where ISBN_Number = ?";
			ps = con.prepareStatement(del);
			ps.setString(1, is);
			ps.executeUpdate();
			System.out.println("<-------- Book Removed Successfully! ✔️ ------->");
		}
	}

	// Borrow book
	public void borrow(String ttl, String brwr) throws SQLException {
		Connection con = getConnect();
		String query = "SELECT BookID FROM Books WHERE Book_Title = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, ttl);
		ResultSet rs = ps.executeQuery();
//		int bookID = 0;
		if (rs.next()) {
			int bookID = rs.getInt("BookID");

		String bor = "INSERT INTO Borrowers(BookID, Borrower_Name) VALUES(?,?)";
		ps = con.prepareStatement(bor);
		ps.setInt(1, bookID);
		ps.setString(2, brwr);
		ps.executeUpdate();

		String sub = "UPDATE Books SET Available_Copies = Available_Copies - 1 Where BookID = ?";
		ps = con.prepareStatement(sub);
		ps.setInt(1, bookID);
		ps.executeUpdate();

		System.out.println("\n<-------- Book Issued Successfully! ✔️ ------->");
		System.out.println("<-------- Enjoy Reading the Book 😊 ------->");
		}
		else {
			System.out.println("<------- ❌No Book Available of this Book Title. ------->");
		}
	}
	
	// return book
	public void returnB(String ttl, String brwr) throws SQLException{
		 Connection con = getConnect();

        String query = "SELECT Borrower_Name FROM Borrowers WHERE Borrower_Name = ? AND Return_Date IS NULL";
         ps = con.prepareStatement(query);
         ps.setString(1, brwr);
         ResultSet rs = ps.executeQuery(); 
         if(rs.next()) {
         String ret = "UPDATE Borrowers SET Return_Date = CURRENT_DATE Where Borrower_Name = ?";
         ps = con.prepareStatement(ret);
         ps.setString(1, brwr);
         ps.executeUpdate();

         String add = "UPDATE Books SET Available_Copies = Available_Copies + 1 Where Book_Title = ?";
         ps = con.prepareStatement(add);
         ps.setString(1,ttl);
         ps.executeUpdate();
         
         System.out.println("\n<-------- Book Returned Successfully! ✔️ ------->");
         System.out.println("<-------- Hope you Enjoyed Reading. 😊 ------->");
         }
         else {
        	 System.out.println("<------- ❌No Book Available of this Book Title to Return. ------->");
         }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
