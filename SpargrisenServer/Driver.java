package SpargrisenServer;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Spara.InformationHandler;

public class Driver {
	
	private static Connection con;
	private static Statement stat;
	private static ResultSet resSet;
	
	/*
	 * För att få denna klassen att fungera så måste ni ladda ner mysql-connector-java-5.1.35.zip.
	 * Finns på mysql sidan. http://dev.mysql.com/downloads/connector/j/
	 * 
	 * välj platform independant och välj att ladda ner zipen, (måste inte registrera sig).
	 * 
	 * packa upp zippen.
	 * 
	 * Högerklicka på javaprojektet som ni vill använda connectorn i. välj new sen folder som man kan kalla lib (library).
	 * Kopiera in mysql-connector-java-5.1.35-bin.jar i foldern.
	 * 
	 * högerklicka igen på projektet och välj properties.
	 * Välj Java Build Path.
	 * Tryck på "Add JARs"
	 * 
	 * Hitta nu foldern som ni döpt till lib eller liknande i projektet som ni vill pyssla med.
	 * Markera Jarfilen tryck ok ok. nu bör ni kunna använda denna klassen.
	 * 
	 * 
	 * 	För extra Hjälp kolla denna youtube videon 
	 * ( OBS han använder inte static metoder vilket inte verkar funka  OBS)
	 *  https://www.youtube.com/watch?v=2i4t-SL1VsU
	 *  
	 *  och denna sidan
	 *  http://www.tutorialspoint.com/jdbc/jdbc-drop-tables.htm
	 */
	
	
	
	
	
	
	/*
	 * Metod som kollar av ifall man kan komma åt DataBasen.
	 * Använd den avmarkerade ifal ni vill komma åt min Databas.
	 * i slutet på Host Stringen efter IP adress och port så är /MinDataBas Schemat där tabbellerna ligger.
	 * funkar även att skriva i de olika metoderna ( MinDatabas.MinTabell ) 
	 * 
	 */
	
	public static void connectionToMysql(){
	//	String host = "jdbc:mysql://94.254.94.236:51515/MinDataBas";
	// For Internet Use	
		
		String userName = "Spargrisen";
		String passWord = "spargrisen";
		String host = "jdbc:mysql://192.168.1.22:51515";
	
		try {
			con = DriverManager.getConnection(host, userName, passWord);
			System.out.println("Connection Works");
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	/*
	 * Ställer en fråga till databasen.  
	 */
	public static void querySelectFrom(String schema, String table){
		try {
			stat = con.createStatement();
			resSet = stat.executeQuery("SELECT * FROM "  + schema + "." + table);

			
			while (resSet.next()){
				System.out.println(resSet.getString("CategoryID")+" "+resSet.getString("CategoryName")+" "+resSet.getString("User")+" "+resSet.getString("Border"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Skapar en Tabell
	 */
	
	public static void createTable(String schema, String table){
		System.out.println("Creating table in given database...");
	   
		try {
			stat = con.createStatement();
	      
	      String sql = "CREATE TABLE "+schema+"."+table+
	                   "(ID INTEGER NOT NULL, " +
	                   " first VARCHAR(255), " + 
	                   " last VARCHAR(255), " + 
	                   " age INTEGER, " + 
	                   " PRIMARY KEY ( ID ))"; 

			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	      System.out.println("Created table in given database...");
	}
	
	/*
	 * Tar bort Tabell ur schema.
	 */
	
	public static void deletTable(String schema, String table){
		System.out.println("Deleteing Table in given database...");
		
		try{
			stat = con.createStatement();
			String sql = "DROP TABLE " + schema + "." + table;
			stat.executeUpdate(sql);
		}catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("Table deleted...");
	}
		
	/*
	 * Uppdaterara tabellen med info.
	 */
	
	public static void updateTable(int ID, String FName, String LName, int age){
		
		System.out.println("Puting Infrormation into table...");
		
		try{
			stat = con.createStatement();
			
			String sql = "INSERT INTO spargrisen.Wanker "+
						 "VALUES("+
						 ID+","+
						 "'"+FName+"'"+","+
						 "'"+LName+"'"+","+
						 age+")";
						 
			stat.executeUpdate(sql);
		}catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("Information uppdated...");
	}
		
	public void newPurchase(String user, String product, int price, String tag, String date,String timeStamp) {
		System.out.println("Puting Infrormation into table...");
		
		try{
			stat = con.createStatement();
			
			String sql = "INSERT INTO spargrisen.purchase "+
						 "VALUES("+
						 "'"+user+"'"+","+
						 "'"+product+"'"+","+
						  price+","+
						  "'"+tag+"'"+","+
						  "'"+date+"'"+","+
						  "'"+timeStamp+"'"+")";
						 
			stat.executeUpdate(sql);
		}catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("Information uppdated...");
	}
		
	public static boolean isUserFree(String userName){
		System.out.println("Cheking if User name exist in system");
		boolean isTrue = true;
		try{
			stat = con.createStatement();
			
			String sql = "SELECT * FROM spargrisen.user";
			
			resSet = stat.executeQuery(sql);
			
			while(resSet.next()){
				if(userName == resSet.getString("UserName")){
					isTrue = false;
				}
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return isTrue;
		
	}
	
	public static void retriveUser(String userName) {
		try{
			stat = con.createStatement();
			
			String sql = "SELECT * FROM spargrisen.User";
			
			resSet = stat.executeQuery(sql);
			
			while(resSet.next()){
				if(userName == resSet.getString("username")){
				
				
				}
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
	
	}
	
	public static void createNewUser(String name, char[] password){
		System.out.println("Addning new user...");
		try{
			stat = con.createStatement();
			String sql = "INSERT INTO spargrisen.user "+
						 "VALUES("+
						 "'"+name+"'"+","+
						 "'"+password+"'"+")";
						 
			stat.executeUpdate(sql);
		}catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("User added...");
	}
	
	public static void addCategory(String categoryName, String userName, int limit) {
		System.out.println("Addning new Category...");
		try{
			stat = con.createStatement();
			String sql = "INSERT INTO spargrisen.category (CategoryID, CategoryName, User, Border) "+
						 "VALUES(NULL, '"+categoryName+"', "+
						 "'"+userName+"', "
						 +limit+")";
			stat.executeUpdate(sql);
		}catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("Category added...");
	}
		
	
	public static void addTag(String userName, String tagName, String CategoryName) {
		System.out.println("Addning new Tag...");
		try{
			stat = con.createStatement();
			String sql = "INSERT INTO spargrisen.tag (TagID, CategoryName, User, Border) "+
						 "VALUES(NULL, '"+userName+"', "+
						 "'"+tagName+"', '"
						 +CategoryName+"')";
			stat.executeUpdate(sql);
		}catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("Tag added...");
	}
		
	
		
		
	
	public static void main(String[] args) {
		connectionToMysql();
//		deletTable("spargrisen", "Wanker");
//		createTable("spargrisen","Wanker");
//		updateTable(1111, "Benny", "Karlsson", 65);
//		updateTable(2222, "Henry", "Moskovice", 18);
//		updateTable(3333, "Gunnar", "PangPang", 30);
//		updateTable(4444, "Hjalmar", "Myrder", 25);
//		updateTable(6666, "Menjie", "MaoBao", 23);
//		querySelectFrom("spargrisen","wanker");
//		createNewUser("Menjie", "pasword");
//		addCategory("mat", "Menjie", 5000);
//		addCategory("spel", "Menjie", 5000);
		querySelectFrom("spargrisen", "category");
	}

	

	
	

}
