import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class LibraryManager {
	
	    private Jdbc dbConnection = new Jdbc();

	    public void addBook(String id, String title, String author, String genre) {
	        String query = "INSERT INTO books (id, title, author, genre, isAvailable) VALUES (?,?, ?, ?, true)";
	        
	        try (Connection conn = dbConnection.connect(); 
	             PreparedStatement pstmt = conn.prepareStatement(query)) {

	            pstmt.setString(1, id);
	            pstmt.setString(2, title);
	            pstmt.setString(3, author);
	            pstmt.setString(4, genre);

	            pstmt.executeUpdate();
	            System.out.println("Book added successfully!");

	        } catch (SQLException e) {
	            System.out.println("Error adding book: " + e.getMessage());
	        }
	    }
	    // Method to search for a book by title
	    public void searchBook(String title) {
	        String query = "SELECT * FROM books WHERE title LIKE ?";
	        
	        try (Connection conn = dbConnection.connect();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {

	            pstmt.setString(1, "%" + title + "%");
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	                System.out.println("Book ID: " + rs.getString("id"));
	                System.out.println("Title: " + rs.getString("title"));
	                System.out.println("Author: " + rs.getString("author"));
	                System.out.println("Genre: " + rs.getString("genre"));
	                System.out.println("Available: " + rs.getBoolean("isAvailable"));
	            }

	        } catch (SQLException e) {
	            System.out.println("Error searching book: " + e.getMessage());
	        }
	        
	    }
	   
	    // Method to issue a book to a user
	    public void issueBook(String bookId, String userId) {
	        String queryCheckAvailability = "SELECT isAvailable FROM books WHERE id = ?";
	        String queryIssueBook = "UPDATE books SET isAvailable = false, issuedTo = ? WHERE id = ?";
	        String queryAddLoan = "INSERT INTO loans (bookId, userId, issueDate, dueDate) VALUES (?, ?, ?, ?)";

	        try (Connection conn = dbConnection.connect()) {

	            // Check if the book is available
	            try (PreparedStatement pstmtCheck = conn.prepareStatement(queryCheckAvailability)) {
	                pstmtCheck.setString(1, bookId);
	                ResultSet rs = pstmtCheck.executeQuery();
	                if (rs.next()) {
	                    boolean isAvailable = rs.getBoolean("isAvailable");
	                    if (!isAvailable) {
	                        System.out.println("Book is already issued.");
	                        return;
	                    }
	                } else {
	                    System.out.println("Book with ID " + bookId + " not found.");
	                    return;
	                }
	            }

	            // Update book availability and issue it to the user
	            try (PreparedStatement pstmtIssue = conn.prepareStatement(queryIssueBook)) {
	                pstmtIssue.setString(1, userId);
	                pstmtIssue.setString(2, bookId);
	                pstmtIssue.executeUpdate();
	            }

	            // Add entry to loans table
	            LocalDate issueDate = LocalDate.now();
	            LocalDate dueDate = issueDate.plusWeeks(2); // Set due date 2 weeks later
	            try (PreparedStatement pstmtAddLoan = conn.prepareStatement(queryAddLoan)) {
	                pstmtAddLoan.setString(1, bookId);
	                pstmtAddLoan.setString(2, userId);
	                pstmtAddLoan.setDate(3, Date.valueOf(issueDate));
	                pstmtAddLoan.setDate(4, Date.valueOf(dueDate));
	                pstmtAddLoan.executeUpdate();
	            }

	            System.out.println("Book with ID " + bookId + " has been issued to user " + userId + ".");

	        } catch (SQLException e) {
	            System.out.println("Error issuing book: " + e.getMessage());
	        }
	    }

	    // Method to return a book
	    public void returnBook(String bookId, String userId) {
	        String queryReturnBook = "UPDATE books SET isAvailable = true, issuedTo = NULL WHERE id = ?";
	        String queryRemoveLoan = "DELETE FROM loans WHERE bookId = ? AND userId = ?";

	        try (Connection conn = dbConnection.connect()) {

	            // Update book availability and remove issuedTo
	            try (PreparedStatement pstmtReturn = conn.prepareStatement(queryReturnBook)) {
	                pstmtReturn.setString(1, bookId);
	                pstmtReturn.executeUpdate();
	            }

	            // Remove entry from loans table
	            try (PreparedStatement pstmtRemoveLoan = conn.prepareStatement(queryRemoveLoan)) {
	                pstmtRemoveLoan.setString(1, bookId);
	                pstmtRemoveLoan.setString(2, userId);
	                pstmtRemoveLoan.executeUpdate();
	            }

	            System.out.println("Book with ID " + bookId + " has been returned by user " + userId + ".");

	        } catch (SQLException e) {
	            System.out.println("Error returning book: " + e.getMessage());
	        }
	    }

	    // Method to exit the prorgram
	    public void exitProgram() {
	        System.out.println("Exiting the program...");
	        System.exit(0);
	    }
	}


