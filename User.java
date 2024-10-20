import java.util.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class User {

	public static void main(String[] args) {
		 LibraryManager library = new LibraryManager();
	        Scanner scanner = new Scanner(System.in);
	        String choice;
	        String userId;
	        String id;
	        String title;
	        String author ;
	        String genre;
	        String bookId;
	        String returnId;

	        do {
	            System.out.println("Welcome to Library Management System");
	            System.out.println("1. Add New Book");
	            System.out.println("2. Search for a Book");
	            System.out.println("3. Issue Book");
	            System.out.println("4. Return Book");
	            System.out.println("5. Exit");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextLine();

	            switch (choice) {
	                case "1":
	                    System.out.print("Enter book ID: ");
	                     id = scanner.nextLine();
	                    System.out.print("Enter book title: ");
	                     title = scanner.nextLine();
	                    System.out.print("Enter author: ");
	                     author = scanner.nextLine();
	                    System.out.print("Enter genre: ");
	                     genre = scanner.nextLine();
	                    library.addBook(id, title, author, genre);
	                    break;

	                case "2":
	                    System.out.print("Enter search title: ");
	                    title= scanner.nextLine();
	                  library.searchBook(title);
	                   // return res;
	                    break;
	                    
			        case "3":
	                    System.out.print("Enter book ID to issue: ");
	                    bookId = scanner.nextLine();
	                    System.out.print("Enter user ID : ");
	                    userId = scanner.nextLine();
	                    library.issueBook(bookId, userId);
	                    break;

	                case "4":
	                    System.out.print("Enter book ID ");
	                     returnId = scanner.nextLine();
	                    System.out.println("user id to return: ");
	                    userId = scanner.nextLine();
	                    library.returnBook(returnId,userId);
	                    break;

	                case "5":
	                    System.out.println("Exiting...");
	                    break;

	                default:
	                    System.out.println("Invalid choice, please try again.");
	            }

	        } while (!choice.equals("5"));

	        scanner.close();
	    }
	

	}


