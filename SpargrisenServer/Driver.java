package SpargrisenServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import SpargrisenObjekt.Category;
import SpargrisenObjekt.CategoryList;

public class Driver {

	private static Connection con;
	private static Statement stat;
	private static ResultSet resSet;

	/*
	 * För att få denna klassen att fungera så måste ni ladda ner
	 * mysql-connector-java-5.1.35.zip. Finns på mysql sidan.
	 * http://dev.mysql.com/downloads/connector/j/
	 * 
	 * välj platform independant och välj att ladda ner zipen, (måste inte
	 * registrera sig).
	 * 
	 * packa upp zippen.
	 * 
	 * Högerklicka på javaprojektet som ni vill använda connectorn i. välj
	 * new sen folder som man kan kalla lib (library). Kopiera in
	 * mysql-connector-java-5.1.35-bin.jar i foldern.
	 * 
	 * högerklicka igen på projektet och välj properties. Välj Java Build
	 * Path. Tryck på "Add JARs"
	 * 
	 * Hitta nu foldern som ni döpt till lib eller liknande i projektet som ni
	 * vill pyssla med. Markera Jarfilen tryck ok ok. nu bör ni kunna använda
	 * denna klassen.
	 * 
	 * 
	 * För extra Hjälp kolla denna youtube videon ( OBS han använder inte
	 * static metoder vilket inte verkar funka OBS)
	 * https://www.youtube.com/watch?v=2i4t-SL1VsU
	 * 
	 * och denna sidan http://www.tutorialspoint.com/jdbc/jdbc-drop-tables.htm
	 */

	/*
	 * Metod som kollar av ifall man kan komma åt DataBasen. Använd den
	 * avmarkerade ifal ni vill komma åt min Databas. i slutet på Host
	 * Stringen efter IP adress och port så är /MinDataBas Schemat där
	 * tabbellerna ligger. funkar även att skriva i de olika metoderna (
	 * MinDatabas.MinTabell )
	 */

	public static void connectionToMysql() {
		// String host = "jdbc:mysql://94.254.94.236:51515/MinDataBas";
		// For Internet Use

		String userName = "Spargrisen";
		String passWord = "spargrisen";
		String host = "jdbc:mysql://94.254.94.236:51515";

		try {
			con = DriverManager.getConnection(host, userName, passWord);
			System.out.println("Connection Works");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTable(String schema, String table) {
		System.out.println("Creating table in given database...");
		try {
			stat = con.createStatement();
			String sql = "CREATE TABLE " + schema + "." + table
					+ "(ID INTEGER NOT NULL, " + " first VARCHAR(255), "
					+ " last VARCHAR(255), " + " age INTEGER, "
					+ " PRIMARY KEY ( ID ))";
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Created table in given database...");
	}

	/*
	 * Tar bort Tabell ur schema.
	 */

	public static void deletTable(String schema, String table) {
		System.out.println("Deleteing Table in given database...");
		try {
			stat = con.createStatement();
			String sql = "DROP TABLE " + schema + "." + table;
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Table deleted...");
	}

	/*
	 * Uppdaterara tabellen med info.
	 */

	public static void updateTable(int ID, String FName, String LName, int age) {
		System.out.println("Puting Infrormation into table...");
		try {
			stat = con.createStatement();
			String sql = "INSERT INTO spargrisen.Wanker " + "VALUES(" + ID
					+ "," + "'" + FName + "'" + "," + "'" + LName + "'" + ","
					+ age + ")";
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Information uppdated...");
	}

	public void newPurchase(String user, String product, int price, String tag, String date, String timeStamp) {
		System.out.println("Puting Infrormation into table...");
		try {
			stat = con.createStatement();
			String sql = "INSERT INTO spargrisen.purchase " + "VALUES(" + "'"
						+ user + "'" + "," + "'" + product + "'" + "," + price
						+ "," + "'" + tag + "'" + "," + "'" + date + "'" + ","
						+ "'" + timeStamp + "'" + ", NULL )";
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Information uppdated...");
	}

	public static boolean isUserFree(String userName) {
		System.out.println("Cheking if Username exist in system");
		boolean isTrue = true;
		try {
			stat = con.createStatement();
			String sql = "SELECT * FROM spargrisen.user";
			resSet = stat.executeQuery(sql);
			while (resSet.next()) {
				if (userName == resSet.getString("UserName")) {
					isTrue = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isTrue;
	}
	
	private static LinkedList<String> getTags(int i){
		LinkedList<String> list = new LinkedList<String>();
		try {
			stat = con.createStatement();
			String sql = "SELECT * FROM spargrisen.tag "
						+"WHERE CategoryID="+i;
			resSet = stat.executeQuery(sql);
			while (resSet.next()) {
				list.add(resSet.getString("CategoryID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static CategoryList retriveUser(String userName, CategoryList catList) {
		try {
			stat = con.createStatement();
			String sql = "SELECT * FROM spargrisen.category";
			resSet = stat.executeQuery(sql);
			int i = 0;
			while (resSet.next()) {
				i = resSet.getInt("CategoryID");
				Category cat = new Category(resSet.getString("CategoryName"), resSet.getInt("Border"), getTags(i));
				catList.addCategory(cat);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return catList;
	}

	public static void createNewUser(String name, char[] password) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < password.length; i++) {
			sb.append(password[i]);
		}
		String fs = sb.toString();
		System.out.println("Addning new user...");
		try {
			stat = con.createStatement();
			String sql = "INSERT INTO spargrisen.user " + "VALUES(" + "'"
						 + name + "'" + "," + "'" + fs + "'" + ")";
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("User added...");
	}

	public static void addCategory(String categoryName, String userName, int limit) {
		System.out.println("Addning new Category...");
		try {
			stat = con.createStatement();
			String sql = "INSERT INTO spargrisen.category (CategoryID, CategoryName, User, Border) "
					+ "VALUES(NULL, '"
					+ categoryName
					+ "', "
					+ "'"
					+ userName
					+ "', " + limit + ")";
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Category added...");
	}

	public static void addTag(String userName, String tagName, String CategoryName) {
		System.out.println("Addning new Tag...");
		int i = 0;
		try {
			stat = con.createStatement();
			String sql = "SELECT * FROM spargrisen.category " + "WHERE User='"
						 + userName + "' " + "AND CategoryName='" + CategoryName+ "'";
			resSet = stat.executeQuery(sql);
			while (resSet.next()) {
				i = resSet.getInt("CategoryID");
			}
			String sql2 = "INSERT INTO spargrisen.tag (CategoryID, TagName, TagID) "
					+ "VALUES(" + i + ", '" + tagName + "' , NULL)";
			stat.executeUpdate(sql2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Tag added...");
	}

	public static void changeBudgetLimit(String categoryName, String userName, int newLimit) {
		System.out.println("Changing limit in category: " + categoryName+ " ...");
		try {
			stat = con.createStatement();
			String sql = "UPDATE spargrisen.category " + "SET Border="
					+ newLimit + " " + "WHERE CategoryName='" + categoryName
					+ "' AND User='" + userName + "';";
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Limit changed...");
	}

	public static void editUser(String userName, String changeTo, char[] newPassword) {
		System.out.println("Editing user: "+userName+"...");
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < newPassword.length; i++) {
			sb.append(newPassword[i]);
		}
		
		String fs = sb.toString();
		
		try {
			stat = con.createStatement();
			String sql = "UPDATE spargrisen.user " 
						+ "SET UserName='"+userName+"', " 
						+"SET Password='"+fs+"',"
						+ "WHERE UserName='" + changeTo+"';";
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		connectionToMysql();
	}
}
