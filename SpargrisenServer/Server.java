package SpargrisenServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import SpargrisenClient.User;

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
	private User user;
	private ClientHandler clientHandler;
	private LinkedList<User> userList;

	public Server(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
		alch = new ArrayList<ClientHandler>();
		userList = new LinkedList<User>();
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
		}

		/**
		 * Metod som startar hantering av inkommande och utgående object
		 * 
		 */
		public void run() {
			Object object = null;
			String purchase;

			try {
				while (true) {

					object = ois.readObject();

					if (object instanceof User) {
						user = (User) object;
						user.setID(clientHandler.getId());
						if (doesUserExist(user)) {
							retriveUser(user);
						} else
							registerUser(user);
					} else if (object instanceof String) {
						purchase = (String) object;
						handleTransaction(purchase);

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

		public void retriveUser(User user) throws IOException {
			User retriveUser = null;
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getName().equals(user.getName())) {
					retriveUser = userList.get(i);
				}
			}
			oos.writeObject(retriveUser);
			oos.flush();
		}

		public boolean doesUserExist(User user) {
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getName().equals(user.getName())) {
					return true;
				}
			}
			return false;
		}

		public void registerUser(User user) throws IOException {
			String register;
			register = "Welcome! " + user.getName() + "\n"
					+ "You have now been succesfully registerd!";
			userList.add(user);
			oos.writeObject(register);
			oos.flush();
			oos.writeObject(user);
			oos.flush();

		}

		public void handleTransaction(String purchase) throws IOException {
			String[] parts = purchase.split(";");
			User newUser = null;
			boolean tagExist = false;
			String userName = parts[0].substring(6, parts[0].length());

			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getName().equals(userName)) {
					newUser = userList.get(i);
				}

				for (int j = 0; j < newUser.getCategoryList().size(); j++) {
					if (newUser.getCategoryList().getCategoryIndex(j)
							.checkTags(parts[3])) {
						newUser.getCategoryList().getCategoryIndex(j)
								.addPurchase(purchase);
						tagExist = true;
					}
				}
				if (tagExist == false) {
					newUser.getCategoryList()
							.getCategoryIndex(
									newUser.getCategoryList().size() - 1)
							.addPurchase(purchase);
					System.out.println("Här");
				}
				
				userList.set(i, newUser);
				user = newUser;
				
			}
			sendUpdatedInfo(user);
		}

		public void sendUpdatedInfo(User user) throws IOException {
//			
//			oos.writeObject(user);
//			oos.flush();
			for (int i = 0; i < alch.size(); i++) {
				if (alch.get(i).getId() == user.getID()) {
					ClientHandler ch = alch.get(i);
					ch.oos.writeObject("Nytt köp");
					ch.oos.flush();
					System.out.println(user.toString());
					ch.oos.reset();
					ch.oos.writeObject(user);
					ch.oos.flush();
					

				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		new Server(3001);

	}
}
