/* Jorge Martinez
 * Daryani, Kapeel
 * Madeyski, George A.
 * Library Program for cs425
 * 
 * SQLCalls
 * ---------------
 * This is a static class to export all commands that access the database
 * from the GUI class that creates the actual GUI
 * 
 *  All of these classes are static and can be access without need to make an Object
 *  
 *  The main purpose of this class is to simplify the code on the GUI Class
 *  
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.sql.*;

public class SQLCalls {
	
	final private static String userid ="jmarti48";
	final private static String passwd ="j2781398";
	private static String userID;
	
	@SuppressWarnings("deprecation")
	public static boolean checkAdmin(){

		JPanel p = new JPanel(new BorderLayout(5,5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("ID", SwingConstants.RIGHT));
        labels.add(new JLabel("Password", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));
        JTextField usernameText = new JTextField("");
        controls.add(usernameText);
        JPasswordField passwordText = new JPasswordField();
        controls.add(passwordText);
        p.add(controls, BorderLayout.CENTER);
        
        int result = JOptionPane.showConfirmDialog(null, p, "Please Login With Your Librarian ID", JOptionPane.OK_CANCEL_OPTION,0,new ImageIcon(GUI.class.getResource("/Pictures/Lock.png")));
        if (result == JOptionPane.OK_OPTION) {
        	// add sql code to pull username and related password and assign them to strings
        	@SuppressWarnings("unused")
			String name = null;
    		String password = null;
    		try{
    			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
    			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
    			
    			PreparedStatement stmt = conn.prepareStatement("select * from librarian where lid = ?");
    			stmt.setInt(1, Integer.parseInt(usernameText.getText()));
    			stmt.executeUpdate();
    			ResultSet rset = stmt.executeQuery();
    			
    			while(rset.next()){
    				password = rset.getString("pwd");
    				name = rset.getString("firstname") + " " + rset.getString("lastname");
    			}
    			
    			stmt.close(); // close Statement and release resources
    			conn.close(); // close Connection and release resources
    			
    		}catch(SQLException | ClassNotFoundException  sqle){
    			System.out.println("SQLException : " + sqle);// handle exceptions 
    		}catch(NumberFormatException e){
    			JOptionPane.showMessageDialog(null, "Wrong Password Or User Name (Please remember to use your numeric ID)");
    	        return false;
    		}

        	if((passwordText.getText().equals(password))){
        		return true;
        	}
        	
         }

		JOptionPane.showMessageDialog(null, "Wrong Password Or User Name (Please remember to use your numeric ID)");
        return false;
 		
	}
	@SuppressWarnings("deprecation")
	public static boolean checkUser(){
		
		JPanel p = new JPanel(new BorderLayout(5,5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("ID", SwingConstants.RIGHT));
        labels.add(new JLabel("Password", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));
        JTextField usernameText = new JTextField("");
        controls.add(usernameText);
        JPasswordField passwordText = new JPasswordField();
        controls.add(passwordText);
        p.add(controls, BorderLayout.CENTER);
        
        int result = JOptionPane.showConfirmDialog(null, p, "Please Login With Your User ID", JOptionPane.OK_CANCEL_OPTION,0,new ImageIcon(GUI.class.getResource("/Pictures/Lock.png")));
        if (result == JOptionPane.OK_OPTION) {
        	// add sql code to pull username and related password and assign them to strings
        	
        	String name = null;
    		String password = null;
    		userID = usernameText.getText();
    		try{
    			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
    			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
    			
    			PreparedStatement stmt = conn.prepareStatement("select * from users where usid = ?");
    			stmt.setInt(1, Integer.parseInt(usernameText.getText()));
    			stmt.executeUpdate();
    			ResultSet rset = stmt.executeQuery();
    			
    			while(rset.next()){
    				password = rset.getString("pwd");
    				name = rset.getString("firstname") + " " + rset.getString("lastname");
    			}
    			
    			stmt.close(); // close Statement and release resources
    			conn.close(); // close Connection and release resources
    			
    		}catch(SQLException | ClassNotFoundException  sqle){
    			System.out.println("SQLException : " + sqle);// handle exceptions 
    		}catch(NumberFormatException e){
    			JOptionPane.showMessageDialog(null, "Wrong Password Or User Name (Please remember to use your numeric ID)");
    	        return false;
    		}

        	if((passwordText.getText().equals(password))){
        		Borrow.setCurrentLoggedIn(name);
        		Return.setCurrentLoggedIn(name);
        		return true;
        	}
         }

		JOptionPane.showMessageDialog(null, "Wrong Password Or User Name (Please remember to use your numeric ID)");
        return false;
	}
	public static Object[][] search(String text, String by, String in){
		switch(in.toLowerCase()){
		case "books":
			return searchBooks(text, by);
		case "journals":
			return searchJournal(text, by);
		case "magazines":
			return searchMagazine(text, by);
		}
		return null;
	}
	public static Object[][] searchBooks(String text, String by){
		Object[][] temp = null;
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd);
			
			Statement stmt = conn.createStatement(); // create Statement object
			
			ResultSet rset = stmt.executeQuery("select * from"
					+ "(select books.bid as bid, books.title as title, books.edition as edition, "
					+ "books.numbercopies as numbercopies, authors.auid as auid, authors.firstname as "
					+ "firstname , authors.lastname as lastname from books cross join authors where books.bid = authors.bid)"
					+ "natural join"
					+ "(select books.bid as bid, publisher.pid as pid, publisher.address as address, publisher.pname as pname, "
					+ "publisher.pdate as pdate from books cross join publisher where books.bid = publisher.bid)");
			
			//check for sub string
			//loop result set to create 3d array
			
			temp = new Object[25][8];
			int i = 0;
			
			switch(by.toLowerCase()){
			case "title":
				while(rset.next() && i < 25){
					if(rset.getString("title").toLowerCase().contains(text.toLowerCase())){
						temp[i] = new Object[] {rset.getString("bid"),rset.getString("NumberCopies"),rset.getString("title"),rset.getString("Edition"),rset.getString("FirstName"), 
								rset.getString("lastName"),rset.getString("pName"), rset.getString("Address"),rset.getString("pDate")};
						i++;
					}
				}
				break;
			case "author":
				while(rset.next() && i < 25){
					if(rset.getString("firstname").toLowerCase().contains(text.toLowerCase())  ||  rset.getString("lastname").toLowerCase().contains(text.toLowerCase())){
						temp[i] = new Object[] {rset.getString("bid"),rset.getString("NumberCopies"),rset.getString("title"),rset.getString("Edition"),rset.getString("FirstName"), 
								rset.getString("lastName"),rset.getString("pName"), rset.getString("Address"),rset.getString("pDate")};
						i++;
					}
				}
				break;
			case "publisher":
				while(rset.next() && i < 25){
					if(rset.getString("pName").toLowerCase().contains(text.toLowerCase())){
						temp[i] = new Object[] {rset.getString("bid"),rset.getString("NumberCopies"),rset.getString("title"),rset.getString("Edition"),rset.getString("FirstName"), 
								rset.getString("lastName"),rset.getString("pName"), rset.getString("Address"),rset.getString("pDate")};
						i++;
					}
				}
				break;
			}

			
			stmt.close(); // close Statement and release resources
			conn.close(); // close Connection and release resources
			
		}catch(SQLException | ClassNotFoundException sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
		}
		return temp;	
	}
	public static Object[][] searchJournal(String text, String by){
		Object[][] temp = null;
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd);
			
			Statement stmt = conn.createStatement(); // create Statement object
			
			ResultSet rset = stmt.executeQuery("  select * from (select jid, title as iTitle from jissues) NATURAL JOIN  "
					+ "(select jid, jtitle, atitle, numbercopies,aFirstName, aLastName from "
					+ " (select jarticles.aid as aid, journal.jid as jid, journal.title as jtitle, jarticles.title as atitle, "
					+ "journal.numbercopies as numberCopies from journal cross join jarticles where jarticles.jid = journal.jid) CROSS JOIN "
					+ "(select authors.aid as aid2, journal.jid as jid2, authors.firstname as aFirstName, authors.lastname as aLastName from "
					+ "authors cross join journal where journal.jid = authors.jid) "
					+ "WHERE aid = aid2 and jid = jid2) NATURAL JOIN (select journal.jid as jid, publisher.pname as pname, "
					+ "publisher.address as address, publisher.pdate as pdate from publisher cross join journal where publisher.jid = journal.jid)");
			
			//check for sub string
			//loop result set to create 3d array
			
			temp = new Object[25][8];
			int i = 0;
			
			switch(by.toLowerCase()){
			case "journal title":
				while(rset.next() && i < 25){
					if(rset.getString("jtitle").toLowerCase().contains(text.toLowerCase())){
						temp[i] = new Object[] {rset.getString("jid"),rset.getString("NumberCopies"),rset.getString("jtitle"),rset.getString("atitle"),
								rset.getString("ititle"),rset.getString("aFirstName"), rset.getString("alastname"),rset.getString("pname"), rset.getString("address"),rset.getString("pdate")};
						i++;
					}
				}
				break;
			
			case "article title":
				while(rset.next() && i < 25){
					if(rset.getString("atitle").toLowerCase().contains(text.toLowerCase())){
						temp[i] = new Object[] {rset.getString("jid"),rset.getString("NumberCopies"),rset.getString("jtitle"),rset.getString("atitle"),
								rset.getString("ititle"),rset.getString("aFirstName"), rset.getString("alastname"),rset.getString("pname"), rset.getString("address"),rset.getString("pdate")};
						i++;
					}
				}
				break;
				
			case "article author":
				while(rset.next() && i < 25){
					if(rset.getString("afirstname").toLowerCase().contains(text.toLowerCase()) || rset.getString("alastname").toLowerCase().contains(text.toLowerCase())){
						temp[i] = new Object[] {rset.getString("jid"),rset.getString("NumberCopies"),rset.getString("jtitle"),rset.getString("atitle"),
								rset.getString("ititle"),rset.getString("aFirstName"), rset.getString("alastname"),rset.getString("pname"), rset.getString("address"),rset.getString("pdate")};
						i++;
					}
				}
				break;
				
			case "publisher":
				while(rset.next() && i < 25){
					if(rset.getString("pname").toLowerCase().contains(text.toLowerCase())){
						temp[i] = new Object[] {rset.getString("jid"),rset.getString("NumberCopies"),rset.getString("jtitle"),rset.getString("atitle"),
								rset.getString("ititle"),rset.getString("aFirstName"), rset.getString("alastname"),rset.getString("pname"), rset.getString("address"),rset.getString("pdate")};
						i++;
					}
				}
				break;
			}
			
			stmt.close(); // close Statement and release resources
			conn.close(); // close Connection and release resources
			
		}catch(SQLException | ClassNotFoundException sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
		}
		return temp;	
	}
	public static Object[][] searchMagazine(String text, String by){
		Object[][] temp = null;
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd);
			
			Statement stmt = conn.createStatement(); // create Statement object
			
			ResultSet rset = stmt.executeQuery("select * from"
					+ "(select magazine.mid as mid, magazine.period as period, magazine.name as name, missues.title as title, "
					+ "magazine.numbercopies as NumberCopies from magazine cross join missues where magazine.mid = missues.mid)"
					+ "natural join"
					+ "(select magazine.mid as mid, editors.firstname as efirstName, editors.lastname as elastName from editors "
					+ "cross join magazine where magazine.mid = editors.mid)"
					+ "natural join"
					+ "(select magazine.mid as mid , contributor.firstname as cFirstName, contributor.lastname as cLastName "
					+ "from contributor cross join magazine where magazine.mid = contributor.mid)");
			
			//check for sub string
			//loop result set to create 3d array
			
			temp = new Object[25][8];
			int i = 0;
			
			switch(by.toLowerCase()){
			case "magazine title":
				while(rset.next() && i < 25){
					if(rset.getString("name").toLowerCase().contains(text.toLowerCase())){
						temp[i] = new Object[] {rset.getString("mid"),rset.getString("NumberCopies"),rset.getString("name"),rset.getString("title"),
								rset.getString("period"),rset.getString("EFIRSTNAME"), rset.getString("elastname"),rset.getString("cfirstname"), rset.getString("clastname")};
						i++;
					}
				}
				break;
			case "issue name":
				while(rset.next() && i < 25){
					if(rset.getString("title").toLowerCase().contains(text.toLowerCase())){
						temp[i] = new Object[] {rset.getString("mid"),rset.getString("NumberCopies"),rset.getString("name"),rset.getString("title"),
								rset.getString("period"),rset.getString("EFIRSTNAME"), rset.getString("elastname"),rset.getString("cfirstname"), rset.getString("clastname")};
						i++;
					}
				}
				break;
			case "editor":
				while(rset.next() && i < 25){
					if(rset.getString("efirstname").toLowerCase().contains(text.toLowerCase()) || rset.getString("elastname").toLowerCase().contains(text.toLowerCase())){
						temp[i] = new Object[] {rset.getString("mid"),rset.getString("NumberCopies"),rset.getString("name"),rset.getString("title"),
								rset.getString("period"),rset.getString("EFIRSTNAME"), rset.getString("elastname"),rset.getString("cfirstname"), rset.getString("clastname")};
						i++;
					}
				}
				break;
			case "contributor":
				while(rset.next() && i < 25){
					if(rset.getString("cfirstname").toLowerCase().contains(text.toLowerCase()) || rset.getString("clastname").toLowerCase().contains(text.toLowerCase())){
						temp[i] = new Object[] {rset.getString("mid"),rset.getString("NumberCopies"),rset.getString("name"),rset.getString("title"),
								rset.getString("period"),rset.getString("EFIRSTNAME"), rset.getString("elastname"),rset.getString("cfirstname"), rset.getString("clastname")};
						i++;
					}
				}
				break;
			}
			
			stmt.close(); // close Statement and release resources
			conn.close(); // close Connection and release resources
			
		}catch(SQLException | ClassNotFoundException sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
		}
		return temp;	
	}
	public static Object[] searchBorrow(String ID, String type){
		
		Object[] temp = new Object[8];
		switch(type.toLowerCase()){
		case "books" :
			try{
				Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd);
	
				PreparedStatement stmt = conn.prepareStatement("select * from"
						+ "(select books.bid as bid, books.title as title, books.edition as edition, "
						+ "books.numbercopies as numbercopies, authors.auid as auid, authors.firstname as "
						+ "firstname , authors.lastname as lastname from books cross join authors where (books.bid = authors.bid) AND (books.bid = ?))"
						+ "natural join"
						+ "(select books.bid as bid, publisher.pid as pid, publisher.address as address, publisher.pname as pname, "
						+ "publisher.pdate as pdate from books cross join publisher where books.bid = publisher.bid)");
    			stmt.setInt(1, Integer.parseInt(ID));
    			stmt.executeUpdate();
    			ResultSet rset = stmt.executeQuery();
    			
				//loop result set to create 3d array
				temp = new Object[] {"","","","","","","","","","",""};
				
				if(rset.next()){
					temp = new Object[] {rset.getString("bid"),rset.getString("NumberCopies"),rset.getString("title"),rset.getString("Edition"),rset.getString("FirstName"), 
							rset.getString("lastName"),rset.getString("pName"), rset.getString("Address"),rset.getString("pDate")};
				}
				stmt.close(); // close Statement and release resources
				conn.close(); // close Connection and release resources
				
			}catch(SQLException | ClassNotFoundException sqle){
				System.out.println("SQLException : " + sqle);// handle exceptions 
			}catch(NumberFormatException e){
				temp = new Object[] {"","","","","","","","","","",""};
    		}			
			break;

		case "journals":
			
			try{
				Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd);
	
				PreparedStatement stmt = conn.prepareStatement("select * from"
						+ "(select journal.jid as jid, journal.title as jtitle, jarticles.title as atitle, journal.numbercopies as numberCopies  "
						+ "from journal cross join jarticles where  (jarticles.jid = journal.jid) AND (jarticles.jid = ?))"
						+ "NATURAL JOIN"
						+ "(select jid, title as iTitle from jissues)"
						+ "NATURAL JOIN"
						+ "(select journal.jid as jid, authors.firstname as aFirstName, authors.lastname as aLastName from authors cross join journal where journal.jid = authors.jid)"
						+ "NATURAL JOIN"
						+ "(select journal.jid as jid, publisher.pname as pname, publisher.address as address, publisher.pdate as pdate from publisher cross join journal where publisher.jid = journal.jid)");
				
    			stmt.setInt(1, Integer.parseInt(ID));
    			stmt.executeUpdate();
    			ResultSet rset = stmt.executeQuery();
    			
				//loop result set to create 3d array
				temp = new Object[] {"","","","","","","","","","",""};
				
				if(rset.next()){
					temp = new Object[] {rset.getString("jid"),rset.getString("NumberCopies"),rset.getString("jtitle"),rset.getString("atitle"),
							rset.getString("ititle"),rset.getString("aFirstName"), rset.getString("alastname"),rset.getString("pname"), rset.getString("address"),rset.getString("pdate")};
				}
				stmt.close(); // close Statement and release resources
				conn.close(); // close Connection and release resources
				
			}catch(SQLException | ClassNotFoundException sqle){
				System.out.println("SQLException : " + sqle);// handle exceptions 
			}catch(NumberFormatException e){
				temp = new Object[] {"","","","","","","","","","",""};
    		}	
			
			break;
		case "magazines":
			
			try{
				Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd);
	
				PreparedStatement stmt = conn.prepareStatement("select * from"
						+ "(select magazine.mid as mid, magazine.period as period, magazine.name as name, missues.title as title, "
						+ "magazine.numbercopies as NumberCopies from magazine cross join missues where (magazine.mid = missues.mid) and (magazine.mid = ?))"
						+ "natural join"
						+ "(select magazine.mid as mid, editors.firstname as efirstName, editors.lastname as elastName from editors "
						+ "cross join magazine where magazine.mid = editors.mid)"
						+ "natural join"
						+ "(select magazine.mid as mid , contributor.firstname as cFirstName, contributor.lastname as cLastName "
						+ "from contributor cross join magazine where magazine.mid = contributor.mid)");
    			stmt.setInt(1, Integer.parseInt(ID));
    			stmt.executeUpdate();
    			ResultSet rset = stmt.executeQuery();
    			
				//loop result set to create 3d array
				temp = new Object[] {"","","","","","","","","","",""};
				
				if(rset.next()){
					temp = new Object[] {rset.getString("mid"),rset.getString("NumberCopies"),rset.getString("name"),rset.getString("title"),
							rset.getString("period"),rset.getString("EFIRSTNAME"), rset.getString("elastname"),rset.getString("cfirstname"), rset.getString("clastname")};
				}
				stmt.close(); // close Statement and release resources
				conn.close(); // close Connection and release resources
				
			}catch(SQLException | ClassNotFoundException sqle){
				System.out.println("SQLException : " + sqle);// handle exceptions 
			}catch(NumberFormatException e){
				temp = new Object[] {"","","","","","","","","","",""};
    		}	
			break;

		}
		return temp;	
	}
	public static boolean Borrow(String iD, String type){
		
		if(iD != null || userID != null){
			switch(type.toLowerCase()){
			case "books":
				try{
	    			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
	    			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
	    			conn.setAutoCommit(false);//turn off autho commit

	    			//regester user as borrowing the book
	    			PreparedStatement stmt = conn.prepareStatement("insert into borrowsBooks values(?,?)");
	    			stmt.setInt(1, Integer.parseInt(userID));
	    			stmt.setInt(2, Integer.parseInt(iD));
	    			stmt.executeUpdate();
	    			stmt.close(); // close Statement and release resources
	    			
	    			//decrement the count to 1
	    			PreparedStatement stmt1 = conn.prepareStatement("update books set numbercopies = numbercopies - 1 where bid = ?");
	    			stmt1.setInt(1, Integer.parseInt(iD));
	    			stmt1.executeUpdate();
	    			stmt1.close(); // close Statement and release resources
	    			
	    			conn.commit(); //commit only if both are successfull
	    			conn.close(); // close Connection and release resources
	    			
	    		}catch(SQLException | ClassNotFoundException  sqle){
	    			if( sqle.toString().equals("java.sql.SQLIntegrityConstraintViolationException: ORA-00001: unique constraint (JMARTI48.SYS_C00268202) violated" + "\n")){
		    			JOptionPane.showMessageDialog(null, "You already have a copy of that document checked out");
	    			}else{
	    				System.out.println("SQLException : " + sqle);// handle exceptions 
	    			}
	    			return false;
	    		}catch(NumberFormatException e){
	    			JOptionPane.showMessageDialog(null, "an error has occurred please try again");
	    	        return false;
	    		}
				break;
			case "journals":
				try{
	    			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
	    			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
	    			conn.setAutoCommit(false);//turn off autho commit
	    			
	    			PreparedStatement stmt = conn.prepareStatement("insert into borrowsjournals values(?,?)");
	    			stmt.setInt(1, Integer.parseInt(userID));
	    			stmt.setInt(2, Integer.parseInt(iD));
	    			stmt.executeUpdate();
	    			stmt.close(); // close Statement and release resources
	    			
	    			//decrement the count to 1
	    			PreparedStatement stmt1 = conn.prepareStatement("update journal set numbercopies = numbercopies - 1 where jid = ?");
	    			stmt1.setInt(1, Integer.parseInt(iD));
	    			stmt1.executeUpdate();
	    			stmt1.close(); // close Statement and release resources
	    			
	    			conn.commit(); //commit only if both are successfull
	    			conn.close(); // close Connection and release resources
	    			
	    		}catch(SQLException | ClassNotFoundException  sqle){
	    			if( sqle.toString().equals("java.sql.SQLIntegrityConstraintViolationException: ORA-00001: unique constraint (JMARTI48.SYS_C00268208) violated" + "\n")){
		    			JOptionPane.showMessageDialog(null, "You already have a copy of that document checked out");
	    			}else{
	    				System.out.println("SQLException : " + sqle);// handle exceptions 
	    			}
	    			return false;
	    		}catch(NumberFormatException e){
	    			JOptionPane.showMessageDialog(null, "an error has occurred please try again");
	    	        return false;
	    		}
				break;
			case "magazines":
				try{
	    			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
	    			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
	    			conn.setAutoCommit(false);//turn off autho commit
	    			
	    			PreparedStatement stmt = conn.prepareStatement("insert into borrowsMagazine values(?,?)");
	    			stmt.setInt(1, Integer.parseInt(userID));
	    			stmt.setInt(2, Integer.parseInt(iD));
	    			stmt.executeUpdate();
	    			stmt.close(); // close Statement and release resources
	    			
	    			//decrement the count to 1
	    			PreparedStatement stmt1 = conn.prepareStatement("update magazine set numbercopies = numbercopies - 1 where mid = ?");
	    			stmt1.setInt(1, Integer.parseInt(iD));
	    			stmt1.executeUpdate();
	    			stmt1.close(); // close Statement and release resources
	    			
	    			conn.commit(); //commit only if both are successfull
	    			conn.close(); // close Connection and release resources
	    			
	    		}catch(SQLException | ClassNotFoundException  sqle){
	    			if( sqle.toString().equals("java.sql.SQLIntegrityConstraintViolationException: ORA-00001: unique constraint (JMARTI48.SYS_C00268205) violated" + "\n")){
		    			JOptionPane.showMessageDialog(null, "You already have a copy of that document checked out");
	    			}else{
	    				System.out.println("SQLException : " + sqle);// handle exceptions 
	    			}
	    			return false;
	    		}catch(NumberFormatException e){
	    			JOptionPane.showMessageDialog(null, "an error has occurred please try again");
	    	        return false;
	    		}
				break;
			}

		}
		return true;
		
	}
	public static Object[][] getBorrowedDocuments(){
		Object[][] temp = null;
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			
			PreparedStatement stmt = conn.prepareStatement("select * from ("
					+ "(select usid,bid as id,Title,'Books' as type from borrowsbooks NATURAL JOIN BOOKS) UNION"
					+ "(select usid,jid as id,Title,'Journals' as type from borrowsjournals NATURAL JOIN journal) UNION"
					+ "(select usid,mid as id,Name as Title,'Magazine' as type from borrowsmagazine NATURAL JOIN magazine)) where usid = ?");
			stmt.setInt(1, Integer.parseInt(userID));
			stmt.executeUpdate();
			ResultSet rset = stmt.executeQuery();

			temp = new Object[25][2];
			int i = 0;
			
			while(rset.next()){
				temp[i] = new Object[] {rset.getString("id"),rset.getString("Title"),rset.getString("Type")};
				i++;
			}
			
			stmt.close(); // close Statement and release resources
			conn.close(); // close Connection and release resources
			
		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
		}catch(NumberFormatException e){
			System.out.println("Internal Error : " + e);// handle exceptions
		}
		
		return temp;
	}
	public static boolean returnDocument(String id, String type){
		switch(type){
		case "Books" :
			try{
				Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
    			conn.setAutoCommit(false);//turn off autho commit
				
				PreparedStatement stmt = conn.prepareStatement("update books "
						+ "set numbercopies = numbercopies + 1"
						+ "where bid = ?");
				stmt.setInt(1, Integer.parseInt(id));
				stmt.executeUpdate();
				stmt.close(); // close Statement and release resources
				
				PreparedStatement stmt1 = conn.prepareStatement("delete from borrowsbooks where (usid = ?) AND (bid = ?)");
				stmt1.setInt(1, Integer.parseInt(userID));
				stmt1.setInt(2, Integer.parseInt(id));
				stmt1.executeUpdate();
				
				stmt1.close(); // close Statement and release resources
    			conn.commit(); //commit only if both are successfull
				conn.close(); // close Connection and release resources
				return true;
				
			}catch(SQLException | ClassNotFoundException  sqle){
				System.out.println("SQLException : " + sqle);// handle exceptions 
			}catch(NumberFormatException e){
				System.out.println("Internal Error : " + e);// handle exceptions
			}
			
			break;
		case "Journals" :
			try{
				Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
    			conn.setAutoCommit(false);//turn off autho commit
				
				PreparedStatement stmt = conn.prepareStatement("update journal "
						+ "set numbercopies = numbercopies + 1"
						+ "where jid = ?");
				stmt.setInt(1, Integer.parseInt(id));
				stmt.executeUpdate();
				stmt.close(); // close Statement and release resources
				
				PreparedStatement stmt1 = conn.prepareStatement("delete from borrowsjournals where (usid = ?) AND (jid = ?)");
				stmt1.setInt(1, Integer.parseInt(userID));
				stmt1.setInt(2, Integer.parseInt(id));
				stmt1.executeUpdate();
				
				stmt1.close(); // close Statement and release resources
    			conn.commit(); //commit only if both are successfull
				conn.close(); // close Connection and release resources
				return true;
				
			}catch(SQLException | ClassNotFoundException  sqle){
				System.out.println("SQLException : " + sqle);// handle exceptions 
			}catch(NumberFormatException e){
				System.out.println("Internal Error : " + e);// handle exceptions
			}
			break;
			
		case "Magazine":
			try{
				Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
    			conn.setAutoCommit(false);//turn off autho commit
				
				PreparedStatement stmt = conn.prepareStatement("update magazine "
						+ "set numbercopies = numbercopies + 1"
						+ "where mid = ?");
				stmt.setInt(1, Integer.parseInt(id));
				stmt.executeUpdate();
				stmt.close(); // close Statement and release resources
				
				PreparedStatement stmt1 = conn.prepareStatement("delete from borrowsmagazine where (usid = ?) AND (mid = ?)");
				stmt1.setInt(1, Integer.parseInt(userID));
				stmt1.setInt(2, Integer.parseInt(id));
				stmt1.executeUpdate();
				
				stmt1.close(); // close Statement and release resources
    			conn.commit(); //commit only if both are successfull
				conn.close(); // close Connection and release resources
				return true;
				
			}catch(SQLException | ClassNotFoundException  sqle){
				System.out.println("SQLException : " + sqle);// handle exceptions 
			}catch(NumberFormatException e){
				System.out.println("Internal Error : " + e);// handle exceptions
			}
			
			break;
		}
		
	return false;
}
	public static boolean addBook(String title, String num, String edit, String pubn, String puba, String pubd, String[] af, String[] al){
		
		int bookid = 0;
		
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			conn.setAutoCommit(false);//turn off autho commit
			
			//check if a book with same title and edition exist
			PreparedStatement stmt0 = conn.prepareStatement("select * from (select LOWER(title)as title from books where (edition = ?)) where title = ?");
			stmt0.setInt(1, Integer.parseInt(edit));
			stmt0.setString(2, title.toLowerCase());
			stmt0.execute();
			ResultSet rset0 = stmt0.executeQuery();
			//System.out.println(rset0.next());
				if(rset0.next()){//if it does print message drop out
					JOptionPane.showMessageDialog(null, "A copy of this title and edition already exist, Please use edit tab to change number of copies");
					rset0.close();
					conn.commit(); //commit only if both are successfull
					conn.close(); // close Connection and release resources
					return false;	
				}else{
					rset0.close();
					
					//get bookid
					
					Statement bookstmt = conn.createStatement();
					ResultSet rset = bookstmt.executeQuery("select max(bid) + 1 as bid from books");
					while(rset.next()){
						bookid = rset.getInt("bid");
					}
					rset.close();
					
					//insert book
					PreparedStatement stmt1 = conn.prepareStatement("insert into books values (?,?,?,?)"); // bid,BOOK NAME, EDITION, NUM COPIES
					stmt1.setInt(1, bookid);
					stmt1.setString(2, title);
					stmt1.setInt(3, Integer.parseInt(edit));
					stmt1.setInt(4, Integer.parseInt(num));
					stmt1.execute();
					stmt1.close(); // close Statement and release resources
					
					
						int i;
						for(i = 0; i < af.length;i++){
							//insert author
							PreparedStatement stmt2 = conn.prepareStatement("insert into authors values ((select max(auid) + 1 from authors),?,null,null,?,?)"); // bid ,firstname, lastname
							stmt2.setInt(1, bookid);
							stmt2.setString(2, af[i]);
							stmt2.setString(3, al[i]);
							stmt2.execute();
							stmt2.close(); // close Statement and release resources
						}
					
					
					//insert publisher
					PreparedStatement stmt3 = conn.prepareStatement("insert into publisher values ((select max(pid) + 1 from publisher),"
							+ "?,null,null,null,?,?,?)"); //bid, address,name,date
					stmt3.setInt(1, bookid);
					stmt3.setString(2, puba);
					stmt3.setString(3, pubn);
					stmt3.setString(4, pubd);
					stmt3.execute();
					stmt3.close(); // close Statement and release resources
					
					
					
					conn.commit(); //commit only if both are successfull
					conn.close(); // close Connection and release resources
					return true;
				}	
		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
			return false;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please ensure edition and number of copies are whole numbers");
			return false;
		}
	}
	public static boolean addJournal(String title, String num, String edit, String pubn, String puba, String pubd, String[] af,
			String[] al, String je, String je1, String[] articleArray) {
	int jid = 0;
	int jiid = 0;
	int aid = 0;
	int auid = 0;
	int eid = 0;
	int pid = 0;
	int i;
		
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			conn.setAutoCommit(false);//turn off auto commit
			
			//check if a journal exist
			PreparedStatement stmt0 = conn.prepareStatement("select LOWER(title)as title from journal where title = ?");
			stmt0.setString(1, title.toLowerCase());
			ResultSet rset0 = stmt0.executeQuery();
				if(rset0.next()){//if it does print message drop out
					JOptionPane.showMessageDialog(null, "A copy of this Journal already exist, Please use edit tab to change number of copies or add articles");
					rset0.close();
					conn.commit(); //commit only if both are successful
					conn.close(); // close Connection and release resources
					return false;	
				}else{
					rset0.close();
					
					//get jid
					Statement jidstmt = conn.createStatement();
					ResultSet rset = jidstmt.executeQuery("select max(jid) + 1 as jid from journal");
					while(rset.next()){
						jid = rset.getInt("jid");
					}
					rset.close();
					
					//insert journal
					PreparedStatement stmt1 = conn.prepareStatement("insert into journal values (?,?,?)"); // jid, title, numbercopies
					stmt1.setInt(1, jid);
					stmt1.setString(2, title);
					stmt1.setInt(3, Integer.parseInt(num));
					stmt1.execute();
					stmt1.close(); // close Statement and release resources
					
								
					//get jjid
					Statement jiidstmt = conn.createStatement();
					ResultSet jiidrset = jiidstmt.executeQuery("select max(jiid) + 1 as jiid from jissues");
					while(jiidrset.next()){
						jiid = jiidrset.getInt("jiid");
					}
					jiidrset.close();
									
					//insert jissues
					PreparedStatement stmtj = conn.prepareStatement("insert into jissues values (?,?,?)"); // jiid, jid, title
					stmtj.setInt(1, jid);
					stmtj.setInt(2, jiid);
					stmtj.setString(3, edit);
					stmtj.execute();
					stmtj.close(); // close Statement and release resources		
					
					//insert j articles + authors
						//get aid
						//insert articles
						//get auid
						//insert author for articles
						//repeat
					for (i = 0; i<articleArray.length;i++){
						
						//get aid
						Statement aidstmt = conn.createStatement();
						ResultSet aidrset = aidstmt.executeQuery("select max(aid) + 1 as aid from jarticles");
						while(aidrset.next()){
							aid = aidrset.getInt("aid");
						}
						aidrset.close();
						
						//insert articles
						PreparedStatement articlestmt = conn.prepareStatement("insert into jarticles values (?,?,?)"); // jid,aid,title
						articlestmt.setInt(1, jid);
						articlestmt.setInt(2, aid);
						articlestmt.setString(3, articleArray[i]);
						articlestmt.execute();
						articlestmt.close(); // close Statement and release resources
						
						//get auid
						Statement auidstmt = conn.createStatement();
						ResultSet auidrset = auidstmt.executeQuery("select max(auid) + 1 as auid from authors");
						while(auidrset.next()){
							auid = auidrset.getInt("auid");
						}
						auidrset.close();
							
						//insert author for articles
						PreparedStatement authorstmt = conn.prepareStatement("insert into authors values (?,null,?,?,?,?)"); // auid,bid,aid,jid,firstname,lastname
						authorstmt.setInt(1, auid);
						authorstmt.setInt(2, aid);
						authorstmt.setInt(3, jid);
						authorstmt.setString(4, af[i]);
						authorstmt.setString(5, al[i]);
						authorstmt.execute();
						authorstmt.close(); // close Statement and release resources'				
					}
					
					//insert into editors values (eid,jiid,jid,miid,mid,firstname,lastname);
					//get eid
					Statement eidstmt = conn.createStatement();
					ResultSet eidrset = eidstmt.executeQuery("select max(eid) + 1 as eid from editors");
					while(eidrset.next()){
						eid = eidrset.getInt("eid");
					}
					eidrset.close();
									
					//insert jeditor
					PreparedStatement stmte = conn.prepareStatement("insert into editors values (?,?,?,null,null,?,?)"); // eid,jiid,jid,miid,mid,firstname,lastname
					stmte.setInt(1, eid);
					stmte.setInt(2, jiid);
					stmte.setInt(3, jid);
					stmte.setString(4,je);
					stmte.setString(5, je1);
					stmte.execute();
					stmte.close(); // close Statement and release resources	

					//insert into publisher values (pid,bid,jiid,jid,mid,address,pname,pdate);
					//get publisher
					Statement pubstmt = conn.createStatement();
					ResultSet pubrset = pubstmt.executeQuery("select max(pid) + 1 as pid from publisher");
					while(pubrset.next()){
						pid = pubrset.getInt("pid");
					}
					pubrset.close();
									
					//insert jeditor
					PreparedStatement stmtp = conn.prepareStatement("insert into publisher values (?,null,?,?,null,?,?,?)"); // pid,bid,jiid,jid,mid,address,pname,pdate
					stmtp.setInt(1, pid);
					stmtp.setInt(2, jiid);
					stmtp.setInt(3, jid);
					stmtp.setString(4,puba);
					stmtp.setString(5, pubn);
					stmtp.setString(6, pubd);
					stmtp.execute();
					stmtp.close(); // close Statement and release resources
					
					conn.commit(); //commit only if both are successfull
					conn.close(); // close Connection and release resources
					return true;
				}	

		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
			return false;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please ensure edition and number of copies are whole numbers");
			return false;
		}
		
	}
	public static Object[] manageUser(String ID){
		Object[] temp = new Object[] {"","","",""};
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			
			PreparedStatement stmt = conn.prepareStatement("select * from Users where USID = ?");
			stmt.setInt(1, Integer.parseInt(ID));
			ResultSet rset = stmt.executeQuery();
			
			while(rset.next()){
				temp = new Object[] {rset.getString("usid"),rset.getString("FirstName"),rset.getString("LastName"),rset.getString("pwd")};
			}
			
			stmt.close();
			conn.close(); // close Connection and release resources
		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please ensure USER ID is correct");	
		}
		return temp;
	}
	public static boolean editUser(Object[] temp) {
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			
			PreparedStatement stmt = conn.prepareStatement("update users set firstname = ?,lastname = ?,pwd = ? WHERE USID = ?");
			stmt.setString(1,(String) temp[1]);
			stmt.setString(2,(String) temp[2]);
			stmt.setString(3,(String) temp[3]);
			stmt.setInt(4,Integer.parseInt((String)temp[0]));
			stmt.executeQuery();
			
			stmt.close();
			conn.close(); // close Connection and release resources
			
			return true;
		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please ensure USER ID is correct");	
		}
		return false;
	}
	public static boolean deleteUser(String ID) {
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			
			PreparedStatement stmt = conn.prepareStatement("delete from users where usid = ?");
			stmt.setInt(1,Integer.parseInt(ID));
			stmt.executeQuery();
			
			stmt.close();
			conn.close(); // close Connection and release resources
			
			return true;
		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please ensure USER ID is correct");	
		}
		return false;
	}
	public static String addUser(Object[] temp) {
		String usid = "";
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			
			Statement stmt0 = conn.createStatement();
			ResultSet rset0 = stmt0.executeQuery("select Max(usid) + 1 as usid from users");
			if(rset0.next()){
				usid = rset0.getString("usid");
			}
			
			PreparedStatement stmt = conn.prepareStatement("insert into users values (?,?,?,?)"); // usid, pwd, first,last
			stmt.setInt(1, Integer.parseInt(usid));
			stmt.setString(2,(String) temp[3]);
			stmt.setString(3,(String) temp[1]);
			stmt.setString(4,(String) temp[2]);
			stmt.executeQuery();
			
			stmt.close();
			conn.close(); // close Connection and release resources
			
			return usid;
		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please ensure USER ID is correct");	
		}
		return "";
	}
	public static boolean editLib(Object[] temp) {
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			
			PreparedStatement stmt = conn.prepareStatement("update librarian set firstname = ?,lastname = ?,pwd = ? WHERE lID = ?");
			stmt.setString(1,(String) temp[1]);
			stmt.setString(2,(String) temp[2]);
			stmt.setString(3,(String) temp[3]);
			stmt.setInt(4,Integer.parseInt((String)temp[0]));
			stmt.executeQuery();
			
			stmt.close();
			conn.close(); // close Connection and release resources
			
			return true;
		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please ensure USER ID is correct");	
		}
		return false;
	}
	public static boolean deleteLib(String ID) {
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			
			PreparedStatement stmt = conn.prepareStatement("delete from librarian where lid = ?");
			stmt.setInt(1,Integer.parseInt(ID));
			stmt.executeQuery();
			
			stmt.close();
			conn.close(); // close Connection and release resources
			
			return true;
		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please ensure ID is correct");	
		}
		return false;
	}
	public static String addLib(Object[] temp) {
		String id = "";
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			
			Statement stmt0 = conn.createStatement();
			ResultSet rset0 = stmt0.executeQuery("select Max(lid) + 1 as lid from librarian");
			if(rset0.next()){
				id = rset0.getString("lid");
			}
			
			PreparedStatement stmt = conn.prepareStatement("insert into librarian values (?,?,?,?)"); // usid, pwd, first,last
			stmt.setInt(1, Integer.parseInt(id));
			stmt.setString(2,(String) temp[3]);
			stmt.setString(3,(String) temp[1]);
			stmt.setString(4,(String) temp[2]);
			stmt.executeQuery();
			
			stmt.close();
			conn.close(); // close Connection and release resources
			
			return id;
		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please ensure ID is correct");	
		}
		return "";
	}
	public static Object[] manageLib(String ID){
		Object[] temp = new Object[] {"","","",""};
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			
			PreparedStatement stmt = conn.prepareStatement("select * from librarian where lID = ?");
			stmt.setInt(1, Integer.parseInt(ID));
			ResultSet rset = stmt.executeQuery();
			
			while(rset.next()){
				temp = new Object[] {rset.getString("lid"),rset.getString("FirstName"),rset.getString("LastName"),rset.getString("pwd")};
			}
			
			stmt.close();
			conn.close(); // close Connection and release resources
		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please ensure USER ID is correct");	
		}
		return temp;
	}
	public static boolean addMagazine(String title, String numb, String ititle,
			String pubn, String puba, String pubd, String editor,
			String editor1, String contrib, String contrib1, String period) {
		
		int mid = 0;
		int miid = 0;
		
		/*
		insert into magazine  values (mid,period,name,numbercompies);
		insert into missues values (miid,mid,title);
		insert into contributor values (cid,miid,mid,firstname,lastname);
		insert into editors values (eid,jiid,jid,miid,mid,firstname,lastname);
		insert into publisher values (pid,bid,jiid,jid,mid,address,pname,pdate);
		 */

		
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			conn.setAutoCommit(false);//turn off auto commit
			
			
			//get mid
			Statement midstmt = conn.createStatement();
			ResultSet mrset = midstmt.executeQuery("select max(mid) + 1 as mid from magazine");
			while(mrset.next()){
				mid = mrset.getInt("mid");
			}
			mrset.close();
			
			//insert magazine
			PreparedStatement stmtm = conn.prepareStatement("insert into magazine  values (?,?,?,?)"); // mid,period,name,numbercompies
			stmtm.setInt(1, mid);
			stmtm.setString(2, period);
			stmtm.setString(3, title);
			stmtm.setString(4, numb);
			stmtm.execute();
			stmtm.close(); // close Statement and release resources
			
			//get miid
			Statement miidstmt = conn.createStatement();
			ResultSet mirset = miidstmt.executeQuery("select max(miid) + 1 as miid from missues");
			while(mirset.next()){
				miid = mirset.getInt("miid");
			}
			mirset.close();
			
			//insert missues
			PreparedStatement stmtmi = conn.prepareStatement("insert into missues values (?,?,?)"); // miid,mid,title
			stmtmi.setInt(1, miid);
			stmtmi.setInt(2, mid);
			stmtmi.setString(3, ititle);
			stmtmi.execute();
			stmtmi.close(); // close Statement and release resources
			
			//insert contributors
			PreparedStatement stmtc = conn.prepareStatement("insert into contributor values ((select max(cid) + 1 as cid from contributor),?,?,?,?)"); // cid,miid,mid,firstname,lastname
			stmtc.setInt(1, miid);
			stmtc.setInt(2, mid);;
			stmtc.setString(3, contrib);
			stmtc.setString(4, contrib1);
			stmtc.execute();
			stmtc.close(); // close Statement and release resources
			
			//insert editors
			PreparedStatement stmte = conn.prepareStatement("insert into editors values ((select max(eid) + 1 as eid from editors),null,null,?,?,?,?)"); // eid,jiid,jid,miid,mid,firstname,lastname
			stmte.setInt(1, miid);
			stmte.setInt(2, mid);;
			stmte.setString(3, editor);
			stmte.setString(4, editor1);
			stmte.execute();
			stmte.close(); // close Statement and release resources
			
			//insert missues
			PreparedStatement stmtp = conn.prepareStatement("insert into publisher values ((select max(pid) + 1 as pid from publisher),null,null,null,?,?,?,?)"); //pid,bid,jiid,jid,mid,address,pname,pdate
			stmtp.setInt(1, mid);
			stmtp.setString(2, puba);
			stmtp.setString(3, pubn);
			stmtp.setString(4, pubd);
			stmtp.execute();
			stmte.close(); // close Statement and release resources			

			conn.commit(); //commit only if both are successfull
			conn.close(); // close Connection and release resources
			return true;

		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
			return false;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please ensure edition and number of copies are whole numbers");
			return false;
		}
	}
	public static Object[] searchBookEdit(int id) {
		
		Object[] temp  = {"","","","","","","","","","","","","","","",""};
		
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			conn.setAutoCommit(false);//turn off auto commit
			
			//return book info
			PreparedStatement stmt = conn.prepareStatement("select * from"
					+ "(select books.bid as bid, books.title as title, books.edition as edition, "
					+ "books.numbercopies as numbercopies, authors.auid as auid, authors.firstname as "
					+ "firstname , authors.lastname as lastname from books cross join authors where  (books.bid = authors.bid) AND (books.bid = ?))"
					+ "natural join"
					+ "(select books.bid as bid, publisher.pid as pid, publisher.address as address, publisher.pname as pname, "
					+ "publisher.pdate as pdate from books cross join publisher where books.bid = publisher.bid)");
			
			stmt.setInt(1, id);	
			
			ResultSet rset = stmt.executeQuery();

			if(rset.next()){			
				Object[] aux = new Object[] {rset.getString("bid"),rset.getString("NumberCopies"),rset.getString("title"),rset.getString("Edition"),rset.getString("FirstName"), 
						rset.getString("lastName"),rset.getString("pName"), rset.getString("Address"),rset.getString("pDate")};
				temp = aux;
			}
			
			while(rset.next()){				
				temp[4] = temp[4].toString() + "," + rset.getString("FirstName");
				temp[5] = temp[5].toString() + "," + rset.getString("lastName");
			}
			
			
			conn.commit(); //commit only if both are successfull
			conn.close(); // close Connection and release resources
			return temp;

		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions	
		}
		
		return temp;		
	}
	public static Object[] searchJournalsEdit(int id) {
		
		Object[] temp1 = {"","","" , "","","","","","","","","","","","",""};
		
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			conn.setAutoCommit(false);//turn off auto commit
			
			//return book info
			PreparedStatement stmt = conn.prepareStatement("  select * from  (select jid, title as iTitle from jissues) NATURAL JOIN  "
					+ "(select jid, jtitle, atitle, numbercopies,aFirstName, aLastName from (select jarticles.aid as aid, journal.jid as jid,"
					+ " journal.title as jtitle, jarticles.title as atitle, journal.numbercopies as numberCopies from journal cross join jarticles "
					+ "where jarticles.jid = journal.jid)  CROSS JOIN (select authors.aid as aid2, journal.jid as jid2, authors.firstname as aFirstName,"
					+ " authors.lastname as aLastName from authors cross join journal where journal.jid = authors.jid) WHERE aid = aid2 and jid = jid2) "
					+ "NATURAL JOIN (select journal.jid as jid, publisher.pname as pname, publisher.address as address, publisher.pdate as pdate from "
					+ "publisher cross join journal where publisher.jid = journal.jid) natural join (select journal.jid as jid, editors.firstname as efirstname, "
					+ "editors.lastname as elastname from editors cross join journal where journal.jid = editors.jid) where jid = ?");
			
			stmt.setInt(1, id);	
			
			ResultSet rset = stmt.executeQuery();
			
			if(rset.next()){
				Object[] aux = new Object[] {rset.getString("jid"),rset.getString("NumberCopies"),rset.getString("jtitle"),rset.getString("atitle"),rset.getString("aFirstName"), 
						rset.getString("aLastName"),rset.getString("pName"), rset.getString("Address"),rset.getString("pDate"), rset.getString("efirstname"), rset.getString("elastname"),rset.getString("ititle")};
				temp1 = aux;
			}
			
			while(rset.next()){	
				temp1[3] = temp1[3].toString() + "," + rset.getString("atitle");
				temp1[4] = temp1[4].toString() + "," + rset.getString("aFirstName");
				temp1[5] = temp1[5].toString() + "," + rset.getString("alastName");
			}
			
			
			conn.commit(); //commit only if both are successfull
			conn.close(); // close Connection and release resources
			return temp1;

		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions	
		}
		
		return temp1;
		
	}
	public static Object[] searchMagazinesEdit(int id) {
		
		Object[] temp2  = {"","","" , "","","","","","","","","","","","",""};
		
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			conn.setAutoCommit(false);//turn off auto commit
			
			//return book info
			PreparedStatement stmt = conn.prepareStatement("select * from (select magazine.mid as mid, magazine.period as period, magazine.name as name, missues.title as title, "
					+ "magazine.numbercopies as NumberCopies from magazine cross join missues where magazine.mid = missues.mid) natural join "
					+ "(select magazine.mid as mid, editors.firstname as efirstName, editors.lastname as elastName from editors  "
					+ "cross join magazine where magazine.mid = editors.mid)"
					+ "natural join (select magazine.mid as mid , contributor.firstname as cFirstName, contributor.lastname as cLastName "
					+ "from contributor cross join magazine where magazine.mid = contributor.mid) natural join (select * from publisher cross join magazine where "
					+ "magazine.mid = publisher.mid)where mid = ? ");
			
			stmt.setInt(1, id);	
			
			ResultSet rset = stmt.executeQuery();
			
			if(rset.next()){
				Object[] aux = new Object[] {rset.getString("mid"),rset.getString("NumberCopies"),rset.getString("title"),rset.getString("name"),rset.getString("eFirstName"), 
						rset.getString("eLastName"),rset.getString("pName"), rset.getString("Address"),rset.getString("pDate"), rset.getString("cFirstName"), rset.getString("clastName"),rset.getString("period")};
				temp2 = aux;
			}
			
			conn.commit(); //commit only if both are successfull
			conn.close(); // close Connection and release resources
			return temp2;

		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions	
		}
		
		return temp2;
		
	}
	public static boolean deleteBook(int id) {
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			conn.setAutoCommit(false);//turn off auto commit
			
			PreparedStatement stmt = conn.prepareStatement("delete from books where bid = ?");
			stmt.setInt(1, id);	
			stmt.executeQuery();
						
			conn.commit(); //commit only if both are successfull
			conn.close(); // close Connection and release resources
			
			return true;
		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions	
		}
		return false;
	}
	public static boolean deleteJournal(int id) {
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			conn.setAutoCommit(false);//turn off auto commit
			
			PreparedStatement stmt = conn.prepareStatement("delete from journal where jid = ?");
			stmt.setInt(1, id);	
			stmt.executeQuery();
						
			conn.commit(); //commit only if both are successfull
			conn.close(); // close Connection and release resources
			
			return true;
		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions	
		}
		return false;
	}
	public static boolean deleteMagazine(int id) {
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			conn.setAutoCommit(false);//turn off auto commit
			
			PreparedStatement stmt = conn.prepareStatement("delete from magazine where mid = ?");
			stmt.setInt(1, id);	
			stmt.executeQuery();
						
			conn.commit(); //commit only if both are successfull
			conn.close(); // close Connection and release resources
			
			return true;
		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions	
		}
		return false;
	}
	public static boolean editMagazine(int id, String title, String numb, String ititle,
			String pubn, String puba, String pubd, String editor,
			String editor1, String contrib, String contrib1, String period) {
		
		int mid = 0;
		int miid = 0;
		
		
		//delete first and then add
		//will rollback if one fails failed
		/*
		insert into magazine  values (mid,period,name,numbercompies);
		insert into missues values (miid,mid,title);
		insert into contributor values (cid,miid,mid,firstname,lastname);
		insert into editors values (eid,jiid,jid,miid,mid,firstname,lastname);
		insert into publisher values (pid,bid,jiid,jid,mid,address,pname,pdate);
		 */

		
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			conn.setAutoCommit(false);//turn off auto commit
			
			
			
			//delete
			PreparedStatement deletestmt = conn.prepareStatement("delete from magazine where mid = ?");
			deletestmt.setInt(1, id);	
			deletestmt.executeQuery();
			
			
			//add
			
			//get mid
			Statement midstmt = conn.createStatement();
			ResultSet mrset = midstmt.executeQuery("select max(mid) + 1 as mid from magazine");
			while(mrset.next()){
				mid = mrset.getInt("mid");
			}
			mrset.close();
			
			//insert magazine
			PreparedStatement stmtm = conn.prepareStatement("insert into magazine  values (?,?,?,?)"); // mid,period,name,numbercompies
			stmtm.setInt(1, mid);
			stmtm.setString(2, period);
			stmtm.setString(3, title);
			stmtm.setString(4, numb);
			stmtm.execute();
			stmtm.close(); // close Statement and release resources
			
			//get miid
			Statement miidstmt = conn.createStatement();
			ResultSet mirset = miidstmt.executeQuery("select max(miid) + 1 as miid from missues");
			while(mirset.next()){
				miid = mirset.getInt("miid");
			}
			mirset.close();
			
			//insert missues
			PreparedStatement stmtmi = conn.prepareStatement("insert into missues values (?,?,?)"); // miid,mid,title
			stmtmi.setInt(1, miid);
			stmtmi.setInt(2, mid);
			stmtmi.setString(3, ititle);
			stmtmi.execute();
			stmtmi.close(); // close Statement and release resources
			
			//insert contributors
			PreparedStatement stmtc = conn.prepareStatement("insert into contributor values ((select max(cid) + 1 as cid from contributor),?,?,?,?)"); // cid,miid,mid,firstname,lastname
			stmtc.setInt(1, miid);
			stmtc.setInt(2, mid);;
			stmtc.setString(3, contrib);
			stmtc.setString(4, contrib1);
			stmtc.execute();
			stmtc.close(); // close Statement and release resources
			
			//insert editors
			PreparedStatement stmte = conn.prepareStatement("insert into editors values ((select max(eid) + 1 as eid from editors),null,null,?,?,?,?)"); // eid,jiid,jid,miid,mid,firstname,lastname
			stmte.setInt(1, miid);
			stmte.setInt(2, mid);;
			stmte.setString(3, editor);
			stmte.setString(4, editor1);
			stmte.execute();
			stmte.close(); // close Statement and release resources
			
			//insert missues
			PreparedStatement stmtp = conn.prepareStatement("insert into publisher values ((select max(pid) + 1 as pid from publisher),null,null,null,?,?,?,?)"); //pid,bid,jiid,jid,mid,address,pname,pdate
			stmtp.setInt(1, mid);
			stmtp.setString(2, puba);
			stmtp.setString(3, pubn);
			stmtp.setString(4, pubd);
			stmtp.execute();
			stmte.close(); // close Statement and release resources			

			conn.commit(); //commit only if both are successfull
			conn.close(); // close Connection and release resources
			return true;

		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
			return false;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please ensure edition and number of copies are whole numbers");
			return false;
		}
	}
	public static boolean editBook(int id, String title, String num, String edit, String pubn, String puba, String pubd, String[] af, String[] al){
		
		int bookid = 0;
		
		//delete first and then add
				//will rollback if one fails failed
		
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			conn.setAutoCommit(false);//turn off autho commit
			
			
			//delete
			PreparedStatement deletestmt = conn.prepareStatement("delete from books where bid = ?");
			deletestmt.setInt(1, id);	
			deletestmt.executeQuery();
		
			//add
			//check if a book with same title and edition exist
			PreparedStatement stmt0 = conn.prepareStatement("select * from (select LOWER(title)as title from books where (edition = ?)) where title = ?");
			stmt0.setInt(1, Integer.parseInt(edit));
			stmt0.setString(2, title.toLowerCase());
			stmt0.execute();
			ResultSet rset0 = stmt0.executeQuery();
			//System.out.println(rset0.next());
				if(rset0.next()){//if it does print message drop out
					JOptionPane.showMessageDialog(null, "A copy of this title and edition already exist, Please use edit tab to change number of copies");
					rset0.close();
					conn.commit(); //commit only if both are successfull
					conn.close(); // close Connection and release resources
					return false;	
				}else{
					rset0.close();
					
					//get bookid
					
					Statement bookstmt = conn.createStatement();
					ResultSet rset = bookstmt.executeQuery("select max(bid) + 1 as bid from books");
					while(rset.next()){
						bookid = rset.getInt("bid");
					}
					rset.close();
					
					//insert book
					PreparedStatement stmt1 = conn.prepareStatement("insert into books values (?,?,?,?)"); // bid,BOOK NAME, EDITION, NUM COPIES
					stmt1.setInt(1, bookid);
					stmt1.setString(2, title);
					stmt1.setInt(3, Integer.parseInt(edit));
					stmt1.setInt(4, Integer.parseInt(num));
					stmt1.execute();
					stmt1.close(); // close Statement and release resources
					
					
						int i;
						for(i = 0; i < af.length;i++){
							//insert author
							PreparedStatement stmt2 = conn.prepareStatement("insert into authors values ((select max(auid) + 1 from authors),?,null,null,?,?)"); // bid ,firstname, lastname
							stmt2.setInt(1, bookid);
							stmt2.setString(2, af[i]);
							stmt2.setString(3, al[i]);
							stmt2.execute();
							stmt2.close(); // close Statement and release resources
						}
					
					
					//insert publisher
					PreparedStatement stmt3 = conn.prepareStatement("insert into publisher values ((select max(pid) + 1 from publisher),"
							+ "?,null,null,null,?,?,?)"); //bid, address,name,date
					stmt3.setInt(1, bookid);
					stmt3.setString(2, puba);
					stmt3.setString(3, pubn);
					stmt3.setString(4, pubd);
					stmt3.execute();
					stmt3.close(); // close Statement and release resources
					
					
					
					conn.commit(); //commit only if both are successfull
					conn.close(); // close Connection and release resources
					return true;
				}	
		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
			return false;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please ensure edition and number of copies are whole numbers");
			return false;
		}
	}
	public static boolean editJournal(int id, String title, String num, String edit, String pubn, String puba, String pubd, String[] af,
			String[] al, String je, String je1, String[] articleArray) {
	int jid = 0;
	int jiid = 0;
	int aid = 0;
	int auid = 0;
	int eid = 0;
	int pid = 0;
	int i;
		
	
			//delete first and then add
			//will rollback if one fails failed
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver"); // load driver
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", userid, passwd); 
			conn.setAutoCommit(false);//turn off auto commit
			
			
			//delete
			
			PreparedStatement deletestmt = conn.prepareStatement("delete from journal where jid = ?");
			deletestmt.setInt(1, id);	
			deletestmt.executeQuery();
			
			//add
			
			
			
			
			//check if a journal exist
			PreparedStatement stmt0 = conn.prepareStatement("select LOWER(title)as title from journal where title = ?");
			stmt0.setString(1, title.toLowerCase());
			ResultSet rset0 = stmt0.executeQuery();
				if(rset0.next()){//if it does print message drop out
					JOptionPane.showMessageDialog(null, "A copy of this Journal already exist, Please use edit tab to change number of copies or add articles");
					rset0.close();
					conn.commit(); //commit only if both are successful
					conn.close(); // close Connection and release resources
					return false;	
				}else{
					rset0.close();
					
					//get jid
					Statement jidstmt = conn.createStatement();
					ResultSet rset = jidstmt.executeQuery("select max(jid) + 1 as jid from journal");
					while(rset.next()){
						jid = rset.getInt("jid");
					}
					rset.close();
					
					//insert journal
					PreparedStatement stmt1 = conn.prepareStatement("insert into journal values (?,?,?)"); // jid, title, numbercopies
					stmt1.setInt(1, jid);
					stmt1.setString(2, title);
					stmt1.setInt(3, Integer.parseInt(num));
					stmt1.execute();
					stmt1.close(); // close Statement and release resources
					
								
					//get jjid
					Statement jiidstmt = conn.createStatement();
					ResultSet jiidrset = jiidstmt.executeQuery("select max(jiid) + 1 as jiid from jissues");
					while(jiidrset.next()){
						jiid = jiidrset.getInt("jiid");
					}
					jiidrset.close();
									
					//insert jissues
					PreparedStatement stmtj = conn.prepareStatement("insert into jissues values (?,?,?)"); // jiid, jid, title
					stmtj.setInt(1, jid);
					stmtj.setInt(2, jiid);
					stmtj.setString(3, edit);
					stmtj.execute();
					stmtj.close(); // close Statement and release resources		
					
					//insert j articles + authors
						//get aid
						//insert articles
						//get auid
						//insert author for articles
						//repeat
					for (i = 0; i<articleArray.length;i++){
						
						//get aid
						Statement aidstmt = conn.createStatement();
						ResultSet aidrset = aidstmt.executeQuery("select max(aid) + 1 as aid from jarticles");
						while(aidrset.next()){
							aid = aidrset.getInt("aid");
						}
						aidrset.close();
						
						//insert articles
						PreparedStatement articlestmt = conn.prepareStatement("insert into jarticles values (?,?,?)"); // jid,aid,title
						articlestmt.setInt(1, jid);
						articlestmt.setInt(2, aid);
						articlestmt.setString(3, articleArray[i]);
						articlestmt.execute();
						articlestmt.close(); // close Statement and release resources
						
						//get auid
						Statement auidstmt = conn.createStatement();
						ResultSet auidrset = auidstmt.executeQuery("select max(auid) + 1 as auid from authors");
						while(auidrset.next()){
							auid = auidrset.getInt("auid");
						}
						auidrset.close();
							
						//insert author for articles
						PreparedStatement authorstmt = conn.prepareStatement("insert into authors values (?,null,?,?,?,?)"); // auid,bid,aid,jid,firstname,lastname
						authorstmt.setInt(1, auid);
						authorstmt.setInt(2, aid);
						authorstmt.setInt(3, jid);
						authorstmt.setString(4, af[i]);
						authorstmt.setString(5, al[i]);
						authorstmt.execute();
						authorstmt.close(); // close Statement and release resources'				
					}
					
					//insert into editors values (eid,jiid,jid,miid,mid,firstname,lastname);
					//get eid
					Statement eidstmt = conn.createStatement();
					ResultSet eidrset = eidstmt.executeQuery("select max(eid) + 1 as eid from editors");
					while(eidrset.next()){
						eid = eidrset.getInt("eid");
					}
					eidrset.close();
									
					//insert jeditor
					PreparedStatement stmte = conn.prepareStatement("insert into editors values (?,?,?,null,null,?,?)"); // eid,jiid,jid,miid,mid,firstname,lastname
					stmte.setInt(1, eid);
					stmte.setInt(2, jiid);
					stmte.setInt(3, jid);
					stmte.setString(4,je);
					stmte.setString(5, je1);
					stmte.execute();
					stmte.close(); // close Statement and release resources	

					//insert into publisher values (pid,bid,jiid,jid,mid,address,pname,pdate);
					//get publisher
					Statement pubstmt = conn.createStatement();
					ResultSet pubrset = pubstmt.executeQuery("select max(pid) + 1 as pid from publisher");
					while(pubrset.next()){
						pid = pubrset.getInt("pid");
					}
					pubrset.close();
									
					//insert jeditor
					PreparedStatement stmtp = conn.prepareStatement("insert into publisher values (?,null,?,?,null,?,?,?)"); // pid,bid,jiid,jid,mid,address,pname,pdate
					stmtp.setInt(1, pid);
					stmtp.setInt(2, jiid);
					stmtp.setInt(3, jid);
					stmtp.setString(4,puba);
					stmtp.setString(5, pubn);
					stmtp.setString(6, pubd);
					stmtp.execute();
					stmtp.close(); // close Statement and release resources
					
					conn.commit(); //commit only if both are successfull
					conn.close(); // close Connection and release resources
					return true;
				}	

		}catch(SQLException | ClassNotFoundException  sqle){
			System.out.println("SQLException : " + sqle);// handle exceptions 
			return false;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please ensure edition and number of copies are whole numbers");
			return false;
		}
		
	}
	
}