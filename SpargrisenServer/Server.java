package SpargrisenServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import SpargrisenObjekt.Category;
import SpargrisenObjekt.RegisterUser;
import SpargrisenObjekt.AvailableUser;
import SpargrisenObjekt.Tag;

/**
 * Klassen är en "Chat Server" som kan lagra och skicka ut meddelande till
 * anslutna klienter
 * 
 * @author Stefan Tran
 *
 */

public class Server implements Runnable {
	/**
	 * Nödvändinga instansvariabler deklareras
	 */
	private ServerSocket serverSocket;
	private Thread server = new Thread(this);
	private ArrayList<ClientHandler> alch;
	private AvailableUser user;
	private ClientHandler clientHandler;

	public Server(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
		alch = new ArrayList<ClientHandler>();
		server.start();
	}

	public void run() {
		System.out.println("Server Online");
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				ClientHandler ch = new ClientHandler(socket);
				this.clientHandler = ch;
				alch.add(ch);
				ch.start();
			} catch (IOException e) {
				System.err.println(e);
			}
		}

	}

	/**
	 * En inre klass som kan hantera de olika klienter som kopplar in sig till
	 * servern
	 * 
	 * @author Girondins
	 *
	 */
	private class ClientHandler extends Thread {
		private Socket socket;
		private ObjectOutputStream oos;
		private ObjectInputStream ois;
		private InformationHandler is;

		/**
		 * Konstruktor som tar emot rätt socket från klient
		 * 
		 * @param socket
		 *            från ansluten klient
		 * @throws IOException
		 */
		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			is = new InformationHandler();
		}

		/**
		 * Metod som startar hantering av inkommande och utgående object
		 * 
		 */
		public void run() {
			Object object = null;
			Category category;
			RegisterUser registerUser;
			Tag tag = null;
			String purchase;

			try {
				while (true) {

					object = ois.readObject();

					if (object instanceof AvailableUser) {
						user = (AvailableUser) object;
						if(user.getToDo()==1){
							sendUpdatedInfo(is.editUser(user));
						}else
						
						if (is.doesUserExist(user)) {
							sendUpdatedInfo(is.retriveUser(user));
						}else
							wrongUser();
						
						

						}
					else if (object instanceof RegisterUser) {
						registerUser = (RegisterUser) object;
						for(int i = 0 ; i<registerUser.getPassword().length; i++){
						}
						sendUpdatedInfo(is.registerUser(registerUser));
					}
					
					
					 else if (object instanceof String) {
						purchase = (String) object;
						// handleTransaction(purchase);
					} else if (object instanceof Category) {
						category = (Category) object;
						if(category.getToDo() == 1){
							sendUpdatedInfo(is.changeBudgetLimit(category));
							
						}else
						sendUpdatedInfo(is.addCategory(category));
					}
					else if (object instanceof Tag){
						sendUpdatedInfo(is.addTag(tag));
					}

				}
			} catch (IOException | ClassNotFoundException e) {
				System.err.println("fel");
			}
			try {
				oos.close();
				ois.close();
				socket.close();
			} catch (IOException e1) {
				System.out.println("error");
			}

		}

		// public void handleTransaction(String purchase) throws IOException {
		// String[] parts = purchase.split(";");
		// User newUser = null;
		// boolean tagExist = false;
		// String userName = parts[0].substring(6, parts[0].length());
		//
		// for (int i = 0; i < userList.size(); i++) {
		// if (userList.get(i).getName().equals(userName)) {
		// newUser = userList.get(i);
		// }
		//
		// for (int j = 0; j < newUser.getCategoryList().size(); j++) {
		// if (newUser.getCategoryList().getCategoryIndex(j)
		// .checkTags(parts[3])) {
		// newUser.getCategoryList().getCategoryIndex(j)
		// .addPurchase(purchase);
		// tagExist = true;
		// }
		// }
		// if (tagExist == false) {
		// newUser.getCategoryList()
		// .getCategoryIndex(
		// newUser.getCategoryList().size() - 1)
		// .addPurchase(purchase);
		// System.out.println("Här");
		// }
		//
		// userList.set(i, newUser);
		// user = newUser;
		//
		// }
		// sendUpdatedInfo(user);
		// }
		
		public void wrongUser(){
			String message = "You have Entered an Invalid Username/Password";
			
			try {
				oos.writeObject(message);
				oos.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		public void sendUpdatedInfo(AvailableUser user) throws IOException {
			//
			 oos.writeObject(user);
			 oos.flush();
//			for (int i = 0; i < alch.size(); i++) {
//				if (alch.get(i).getId() == user.getID()) {
//					ClientHandler ch = alch.get(i);
//					ch.oos.writeObject("Nytt köp");
//					ch.oos.flush();
//					System.out.println(user.toString());
//					ch.oos.reset();
//					ch.oos.writeObject(user);
//					ch.oos.flush();
//
//				}
//			}
		}

	}

	public static void main(String[] args) throws IOException {
		new Server(3001);

	}
}
