import java.util.*;
public class Book {
	String id;
	String title;
	String author;
	String genre;
	boolean isAvailable;
	String issuedTo;
	
	public Book(String id,String title, String author, String genre){	
			this.id=id;
			this.title=title;
			this.author=author;
			this.genre=genre;
			this.isAvailable=true;
			this.issuedTo="";
		}
public String getId()
{
	return id;
	
}
public String getTitle() {
	return title;
}
public String getAuthor()
{
	return author;
}
public String getGenre()
{
	return genre;
}
public boolean isAvailable()
{
	return isAvailable;
	
}
public String getIssuedTo()
{
	return issuedTo;
}
public void setAvailable(boolean available) {
	this.isAvailable=available;
}
public void setIssuedTo(String issuedTo) {
	this.issuedTo=issuedTo;
}
public String toString()
{
	return "Book ID:" +id+",Title: "+ title+",Author: "+author+",Genre:" +genre+",Available: "+isAvailable;
}
		
}

